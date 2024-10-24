package com.nexu.android.core.data.repository.offline

import com.nexu.android.core.data.model.TodoResource
import kotlinx.coroutines.flow.Flow
import com.nexu.android.core.data.common.result.Result


interface NexuOfflineRepository {

    suspend fun addTodo(todo: TodoResource): Flow<String>
    suspend fun updateTodo(todo: TodoResource): Flow<String>
    suspend fun getAllTodos(): Flow<List<TodoResource>>

}