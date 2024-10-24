package com.nexu.feature.todohome

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexu.android.core.data.model.TodoResource
import com.nexu.android.core.ui.NexuLoading
import com.nexu.feature.todohome.TodoHomeContract.UiState
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoHomeScreen(
    viewModel: TodoHomeViewModel = hiltViewModel(),
) {
    val onEventSent = viewModel::handleEvents
    val context = LocalContext.current

    LaunchedEffect(context, viewModel.effect) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is TodoHomeContract.Effect.ShowError -> {
                    Toast.makeText(context, effect.msg, LENGTH_SHORT).show()
                }

                is TodoHomeContract.Effect.ShowSuccess -> TODO()
            }
        }.collect {}
    }

    val todoList by viewModel.todosListResponse.collectAsStateWithLifecycle()
    val addTodo by viewModel.addTodoResponse.collectAsStateWithLifecycle()
    val updateTodo by viewModel.updateTodoResponse.collectAsStateWithLifecycle()

    Column {
        TodoHomeScreenContent(todoList = todoList, onEventSent = onEventSent)
    }

}

@Composable
fun TodoHomeScreenContent(
    todoList: UiState, onEventSent: (event: TodoHomeContract.Event) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (todoList) {
            is UiState.Failure -> {
                //toast
            }

            UiState.Loading -> {
                NexuLoading(modifier = Modifier.fillMaxSize())
            }

            is UiState.Success -> {
                LazyColumn {
                    items(todoList.getAllTodos?.size ?: 0) {
                        todoList.getAllTodos?.get(it)
                            ?.let { it1 -> TodoListItem(todoItem = it1, {}, {}) }
                    }
                }
            }
        }
    }
}


@Composable
fun TodoListItem(
    todoItem: TodoResource,
    onTodoCheckedChange: (TodoResource) -> Unit,
    onTodoClick: (TodoResource) -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onTodoClick(todoItem) }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {

        Checkbox(checked = isChecked, onCheckedChange = {
            isChecked = it
            onTodoCheckedChange(todoItem.copy(isDone = isChecked))
        })

        Spacer(modifier = Modifier.width(8.dp))


        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = todoItem.title ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = if (isChecked) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f) else MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = todoItem.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = if (isChecked) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f) else MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview
@Composable
fun TodoHomeScreenContentPreview() {
    TodoHomeScreenContent(todoList = UiState.Loading, onEventSent = {})
    //TodoListItem(TodoResource(), {}, {})
}

