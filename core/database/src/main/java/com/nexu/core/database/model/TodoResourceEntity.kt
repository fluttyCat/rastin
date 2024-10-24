package com.nexu.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexu.android.core.data.model.TodoResource

@Entity(tableName = "todos")
data class TodoResourceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val isDone: Boolean = false
)

fun TodoResourceEntity.asExternalModel() = TodoResource(
    id = id,
    title = title,
    description = description,
    isDone = isDone,
)
