package com.nexu.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nexu.android.core.data.common.result.Result
import com.nexu.android.core.data.model.TodoResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {
    val uiState = MutableStateFlow<Boolean>(false)
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(
        val userData: Boolean
    ) : MainActivityUiState
}
