package com.mmb.happybox.data.di

import com.mmb.happybox.data.database.HappyBoxRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideHappyThingDao(
        db: HappyBoxRoomDatabase,
    ) = db.happyThingsDao()

}