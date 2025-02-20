package com.rastin.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rastin.android.core.data.repository.Repository
import com.rastin.android.core.ui.BaseViewModel
import com.rastin.feature.home.HomeContract.Effect
import com.rastin.feature.home.HomeContract.Event
import com.rastin.feature.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel<Event, UiState, Effect>() {


    override fun setInitialViewState() = HomeContract.initValue


    override fun handleEvents(event: Event) {

    }

    private val _marketDepth = MutableStateFlow<String>("")
    val marketDepth: StateFlow<String> get() = _marketDepth

    init {
        viewModelScope.launch {
            repository.connect()

            repository.observeMarketDepth().collect { marketData ->
                _marketDepth.value = marketData
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            repository.disconnect()
        }
    }
}