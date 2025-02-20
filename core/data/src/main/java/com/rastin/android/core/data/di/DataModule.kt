package com.nexu.android.core.data.di

import com.nexu.android.core.data.repository.offline.NexuOfflineRepository
import com.nexu.android.core.data.repository.offline.NexuOfflineRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNexuOfflineRepository(
        repository: NexuOfflineRepositoryImpl
    ): NexuOfflineRepository

}
