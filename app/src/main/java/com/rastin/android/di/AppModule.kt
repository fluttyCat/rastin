package com.rastin.android.di

import com.rastin.android.core.data.common.network.BinanceWebSocketClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWebSocketClient(): BinanceWebSocketClient {
        return BinanceWebSocketClient()
    }

}
