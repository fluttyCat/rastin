package com.nexu.core.database.di

import com.nexu.core.database.TodoDatabase
import com.nexu.core.database.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun providesTodoDao(
        database: TodoDatabase
    ): TodoDao = database.todoDao()

}
