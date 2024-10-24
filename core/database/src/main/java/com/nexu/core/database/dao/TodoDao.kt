package com.nexu.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nexu.core.database.model.TodoResourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoResourceEntity)

    @Update
    suspend fun update(todo: TodoResourceEntity)

    @Query("UPDATE todos SET isDone = :isDone WHERE id = :id")
    suspend fun updateIsDone(id: Int, isDone: Boolean)

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAllTodos(): Flow<List<TodoResourceEntity>>

}