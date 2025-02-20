package com.rastin.feature.todohome

import androidx.lifecycle.viewModelScope
import com.rastin.android.core.data.common.network.BinanceWebSocketClient
import com.rastin.android.core.ui.BaseViewModel
import com.rastin.feature.todohome.HomeContract.Effect
import com.rastin.feature.todohome.HomeContract.Event
import com.rastin.feature.todohome.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val webSocketClient: BinanceWebSocketClient
) : BaseViewModel<Event, UiState, Effect>() {


    override fun setInitialViewState() = HomeContract.initValue


    override fun handleEvents(event: Event) {

    }

    private val _depthData = MutableStateFlow<String?>(null)
    val depthData: StateFlow<String?> = _depthData.asStateFlow()

    private var job: Job? = null

    fun startWebSocket() {
        job?.cancel()
        job = viewModelScope.launch {
            webSocketClient.connect()
                .debounce(5000)
                .collect { data ->
                    _depthData.value = data
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        viewModelScope.launch {
            webSocketClient.disconnect()
        }
    }
}