package com.rastin.feature.todohome

import com.rastin.android.core.ui.ViewEvent
import com.rastin.android.core.ui.ViewSideEffect
import com.rastin.android.core.ui.ViewState

class HomeContract {

    companion object {
        val initValue = UiState.Loading
    }

    sealed interface UiState : ViewState {
        object Success : UiState

        data object Loading : UiState
        data class Failure(val msg: String) : UiState
    }

    sealed interface Event : ViewEvent {

    }

    sealed interface Effect : ViewSideEffect {
        data class ShowError(var msg: String) : Effect
        data class ShowSuccess(val msg: String) : Effect
    }
}
