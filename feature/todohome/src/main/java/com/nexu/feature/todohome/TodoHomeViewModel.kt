package com.nexu.feature.todohome

import androidx.lifecycle.viewModelScope
import com.nexu.android.core.data.common.result.Result
import com.nexu.android.core.data.common.result.asResult
import com.nexu.android.core.data.model.TodoResource
import com.nexu.android.core.domain.nexu.AddTodoUseCase
import com.nexu.android.core.domain.nexu.GetAllTodosUseCase
import com.nexu.android.core.domain.nexu.UpdateTodoUseCase
import com.nexu.android.core.ui.BaseViewModel
import com.nexu.feature.todohome.TodoHomeContract.Effect
import com.nexu.feature.todohome.TodoHomeContract.Event
import com.nexu.feature.todohome.TodoHomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoHomeViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
) : BaseViewModel<Event, UiState, Effect>() {

    private val _todosListState: MutableStateFlow<Result<List<TodoResource>>> =
        MutableStateFlow(Result.Loading)

    val todosListResponse = _todosListState.map { res ->
        when (res) {
            is Result.Error -> {
                setEffect {
                    Effect.ShowError(res.errorMsg)
                }
                UiState.Failure(res.exception?.message.toString())
            }

            Result.Loading -> {
                UiState.Loading
            }

            is Result.Success -> {
                UiState.Success(getAllTodos = res.data)

            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_00),
        UiState.Loading
    )

    private val _addTodoState: MutableStateFlow<Result<String>> =
        MutableStateFlow(Result.Loading)

    val addTodoResponse = _addTodoState.map { res ->
        when (res) {
            is Result.Error -> {
                setEffect {
                    Effect.ShowError(res.errorMsg)
                }
                UiState.Failure(res.exception?.message.toString())
            }

            Result.Loading -> {
                UiState.Loading
            }

            is Result.Success -> {
                UiState.Success(addTodoStatus = res.data)

            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_00),
        UiState.Loading
    )

    private val _updateTodoState: MutableStateFlow<Result<String>> =
        MutableStateFlow(Result.Loading)

    val updateTodoResponse = _updateTodoState.map { res ->
        when (res) {
            is Result.Error -> {
                setEffect {
                    Effect.ShowError(res.errorMsg)
                }
                UiState.Failure(res.exception?.message.toString())
            }

            Result.Loading -> {
                UiState.Loading
            }

            is Result.Success -> {
                UiState.Success(updateTodoStatus = res.data)

            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_00),
        UiState.Loading
    )

    override fun setInitialViewState() = TodoHomeContract.initValue

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            getAllTodosUseCase().asResult().collect {
                _todosListState.emit(it)
            }
        }
    }


    fun addTodo(newTodo: TodoResource) {
        viewModelScope.launch {
            addTodoUseCase(newTodo).asResult().collect() {
                _addTodoState.emit(it)
            }
        }
    }

    fun updateTodos(updateTodo: TodoResource) {
        viewModelScope.launch {
            addTodoUseCase(updateTodo).asResult().collect() {
                _addTodoState.emit(it)
            }
        }
    }

    override fun handleEvents(event: Event) = when (event) {
        is Event.AddTodo -> {
            addTodo(event.todo)
        }

        is Event.UpdateTodo -> {
            updateTodos(event.todo)
        }

        is Event.LoadAllTodos -> {
            fetchTodos()
        }
    }
}