package com.mmb.happybox.data.di

import android.content.Context
import androidx.work.WorkManager
import com.mmb.happybox.data.datasources.HappyThingLocalDataSource
import com.mmb.happybox.data.datasources.HappyThingLocalDataSourceImpl
import com.mmb.happybox.data.repositories.HappyThingRepositoryImpl
import com.mmb.happybox.domain.repositories.HappyThingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HappyThingModule {

    @Binds
    abstract fun provideHappyThingLocalDataSource(impl: HappyThingLocalDataSourceImpl): HappyThingLocalDataSource

    @Binds
    abstract fun provideHappyThingRepository(impl: HappyThingRepositoryImpl): HappyThingRepository

    companion object {

        @Provides
        @Singleton
        fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
            WorkManager.getInstance(context)

    }
}
