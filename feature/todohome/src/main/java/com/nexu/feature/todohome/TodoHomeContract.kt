package com.nexu.feature.todohome

import com.nexu.android.core.data.model.TodoResource
import com.nexu.android.core.ui.ViewEvent
import com.nexu.android.core.ui.ViewSideEffect
import com.nexu.android.core.ui.ViewState

class TodoHomeContract {

    companion object {
        val initValue = UiState.Loading
    }

    sealed interface UiState : ViewState {
        data class Success(
            val getAllTodos: List<TodoResource>? = emptyList(),
            val addTodoStatus: String? = null,
            val updateTodoStatus: String? = null,
        ) : UiState

        data object Loading : UiState
        data class Failure(val msg: String) : UiState
    }

    sealed interface Event : ViewEvent {
        data class AddTodo(val todo: TodoResource) : Event
        data class UpdateTodo(val todo: TodoResource) : Event
        data object LoadAllTodos : Event
    }

    sealed interface Effect : ViewSideEffect {
        data class ShowError(var msg: String) : Effect
        data class ShowSuccess(val msg: String) : Effect
    }
}
