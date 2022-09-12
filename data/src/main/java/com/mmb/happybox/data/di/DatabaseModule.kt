package com.mmb.happybox.data.di

import android.content.Context
import androidx.room.Room
import com.mmb.happybox.data.database.HappyBoxDatabase
import com.mmb.happybox.data.database.HappyBoxRoomDatabase
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
        ): HappyBoxRoomDatabase {
            return Room.databaseBuilder(context, HappyBoxRoomDatabase::class.java, "happy-box-db")
                .build()
        }

        @Provides
        @Singleton
        fun provideHappyThingDao(
            db: HappyBoxRoomDatabase,
        ) = db.happyThingsDao()
    }
}