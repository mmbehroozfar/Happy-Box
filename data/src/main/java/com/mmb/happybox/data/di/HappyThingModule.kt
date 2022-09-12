package com.mmb.happybox.data.di

import com.mmb.happybox.data.datasources.HappyThingLocalDataSource
import com.mmb.happybox.data.datasources.HappyThingLocalDataSourceImpl
import com.mmb.happybox.data.repositories.HappyThingRepositoryImpl
import com.mmb.happybox.domain.repositories.HappyThingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HappyThingModule {

    @Binds
    abstract fun provideHappyThingLocalDataSource(impl: HappyThingLocalDataSourceImpl): HappyThingLocalDataSource

    @Binds
    abstract fun provideHappyThingRepository(impl: HappyThingRepositoryImpl): HappyThingRepository

}
