package com.nexu.android.core.data.repository.offline

import com.rastin.android.core.data.common.result.Result
import com.nexu.android.core.data.model.TodoResource
import com.nexu.core.database.dao.TodoDao
import com.nexu.core.database.model.toEntity
import com.nexu.core.database.model.toResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NexuOfflineRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : NexuOfflineRepository {

    override suspend fun addTodo(todo: TodoResource): Flow<String> = flow {
        try {
            todoDao.insert(todo.toEntity())
            emit("Todo added successfully")
        } catch (e: Exception) {
            emit("Error adding todo: ${e.message}")
        }
    }

    override suspend fun updateTodo(todo: TodoResource): Flow<String> = flow {
        try {
            todoDao.update(todo.toEntity())
            emit("Todo updated successfully")
        } catch (e: Exception) {
            emit("Error updating todo: ${e.message}")
        }
    }

    override suspend fun getAllTodos(): Flow<List<TodoResource>> = flow {
        try {
            todoDao.getAllTodos()
                .collect { todoEntities ->
                    emit(todoEntities.map { it.toResource() })
                }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}