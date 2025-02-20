package com.rastin.android.core.data.repository

import kotlinx.coroutines.flow.Flow


interface Repository {
    fun observeMarketDepth(): Flow<String>
    suspend fun disconnect()
    suspend fun connect()
}