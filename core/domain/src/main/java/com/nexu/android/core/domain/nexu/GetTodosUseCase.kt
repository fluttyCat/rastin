package com.nexu.android.core.domain.nexu

import com.nexu.android.core.data.model.TodoResource
import com.nexu.android.core.data.repository.offline.NexuOfflineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.nexu.android.core.data.common.result.Result


class GetAllTodosUseCase @Inject constructor(
    private val repository: NexuOfflineRepository
) {
    suspend operator fun invoke(): Flow<List<TodoResource>> {
        return repository.getAllTodos()
    }
}

class UpdateTodoUseCase @Inject constructor(
    private val repository: NexuOfflineRepository
) {
    suspend operator fun invoke(todo: TodoResource): Flow<String> {
        return repository.updateTodo(todo)
    }
}

class AddTodoUseCase @Inject constructor(
    private val repository: NexuOfflineRepository
) {
    suspend operator fun invoke(todo: TodoResource): Flow<String> {
        return repository.addTodo(todo)
    }
}