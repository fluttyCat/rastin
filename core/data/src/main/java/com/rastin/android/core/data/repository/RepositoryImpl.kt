package com.rastin.android.core.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val okHttpClient: OkHttpClient
) : Repository {

    private var webSocket: WebSocket? = null
    private val _socketFlow = MutableSharedFlow<String>()
    private var isConnected = false

    override fun observeMarketDepth(): Flow<String> = _socketFlow

   override suspend fun connect() {
        if (isConnected) return

        val request = Request.Builder()
            .url("wss://stream.binance.com:9443/ws/btcusdt@depth")
            .build()

        webSocket = okHttpClient.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                _socketFlow.tryEmit(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("WebSocket", "Error: ${t.message}")
            }
        })

        isConnected = true
    }

    override suspend fun disconnect() {
        if (isConnected) {
            webSocket?.close(1000, "Closing WebSocket")
            isConnected = false
        }
    }
}