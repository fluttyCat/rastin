package com.rastin.android.core.data.common.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BinanceWebSocketClient @Inject constructor() {
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    private var session: DefaultClientWebSocketSession? = null

    suspend fun connect(): Flow<String> = flow {
        try {
            client.webSocket("wss://stream.binance.com:9443/ws/btcusdt@depth") {
                session = this
                for (message in incoming) {
                    val text = (message as? Frame.Text)?.readText()
                    text?.let { emit(it) }
                }
            }
        } catch (e: Exception) {
            Log.e("WebSocket", "Error: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

    suspend fun disconnect() {
        session?.close()
        client.close()
    }
}