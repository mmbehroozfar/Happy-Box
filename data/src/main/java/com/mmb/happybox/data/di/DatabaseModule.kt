package com.mmb.happybox.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.mmb.happybox.data.database.HappyBoxDatabase
import com.mmb.happybox.data.database.HappyBoxRoomDatabase
import com.mmb.happybox.data.worker.DefaultDataGeneratorWorker
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun bindMovieDatabase(
        db: HappyBoxRoomDatabase,
    ): HappyBoxDatabase


    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context,
            workManager: WorkManager,
        ): HappyBoxRoomDatabase =
            Room.databaseBuilder(context, HappyBoxRoomDatabase::class.java, "happy-box-db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<DefaultDataGeneratorWorker>()
                            .addTag(DefaultDataGeneratorWorker.TAG)
                            .build()

                        workManager.enqueueUniqueWork(
                            DefaultDataGeneratorWorker.TAG,
                            ExistingWorkPolicy.REPLACE,
                            request,
                        )
                    }
                })
                .build()

        @Provides
        @Singleton
        fun provideHappyThingDao(
            db: HappyBoxRoomDatabase,
        ) = db.happyThingsDao()

    }
}