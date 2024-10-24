package com.nexu.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexu.android.core.data.model.TodoResource

@Entity(tableName = "todos")
data class TodoResourceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String? = null,
    val description: String? = null,
    val isDone: Boolean? = false
)

fun TodoResourceEntity.toResource() = TodoResource(
    id = id,
    title = title,
    description = description,
    isDone = isDone,
)

fun TodoResource.toEntity(): TodoResourceEntity {
    return TodoResourceEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        isDone = this.isDone
    )
}