package com.nexu.android.core.data.model


data class TodoResource(
    val id: Int = 0,
    val title: String? = null,
    val description: String? = null,
    val isDone: Boolean? = false
)
