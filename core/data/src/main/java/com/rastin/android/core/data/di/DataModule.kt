package com.rastin.android.core.data.di

import com.rastin.android.core.data.repository.Repository
import com.rastin.android.core.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsRepository(
        repository: RepositoryImpl
    ): Repository

}
