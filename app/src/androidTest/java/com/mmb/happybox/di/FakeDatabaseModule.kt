package com.mmb.happybox.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mmb.happybox.data.database.HappyBoxRoomDatabase
import com.mmb.happybox.data.di.DatabaseModule
import com.mmb.happybox.utils.FakeData
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object FakeDatabaseModule {

    @Provides
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, HappyBoxRoomDatabase::class.java
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    FakeData.items.forEach {
                        db.execSQL("INSERT INTO happy_things (name) values('$it')")
                    }
                }
            })
            .allowMainThreadQueries()
            .build()

}