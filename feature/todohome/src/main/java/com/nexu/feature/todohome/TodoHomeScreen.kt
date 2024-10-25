package com.nexu.feature.todohome

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
    navigateToAddTodo: () -> Unit
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
    val updateTodo by viewModel.updateTodoResponse.collectAsStateWithLifecycle()

    LaunchedEffect(updateTodo) {
        if (updateTodo is UiState.Success) {
            viewModel.handleEvents(TodoHomeContract.Event.LoadAllTodos)
        }
    }

    Column {
        TodoHomeScreenContent(
            todoList = todoList,
            updateTodo = updateTodo,
            onEventSent = onEventSent
        ) {
            navigateToAddTodo()
        }
    }

}

@Composable
fun TodoHomeScreenContent(
    todoList: UiState,
    updateTodo: UiState,
    onEventSent: (event: TodoHomeContract.Event) -> Unit,
    onFabClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {

        when (todoList) {
            is UiState.Failure -> {
                // Handle failure, e.g., show a Toast or Snackbar
            }

            UiState.Loading -> {
                Text("Loading...")
                NexuLoading(modifier = Modifier.fillMaxSize())
            }

            is UiState.Success -> {
                if (todoList.getAllTodos?.isNotEmpty() == true) {
                    LazyColumn {
                        items(todoList.getAllTodos.size) { index ->
                            todoList.getAllTodos.get(index)
                                ?.let { todoItem ->
                                    TodoListItem(
                                        todoItem = todoItem,
                                        onTodoCheckedChange = { updatedTodo ->
                                            onEventSent(
                                                TodoHomeContract.Event.UpdateTodo(
                                                    updatedTodo
                                                )
                                            )
                                        },
                                        onTodoClick = {})
                                    Spacer(modifier = Modifier.height(4.dp))
                                }
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("There is no todo")
                    }
                }

            }
        }



        FloatingActionButton(
            onClick = { onFabClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            content = {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Todo")
            }
        )
    }
}


@Composable
fun TodoListItem(
    todoItem: TodoResource,
    onTodoCheckedChange: (TodoResource) -> Unit,
    onTodoClick: (TodoResource) -> Unit
) {
    var isChecked by remember { mutableStateOf(todoItem.isDone) } // Set initial state based on todoItem

    Row(modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, MaterialTheme.colorScheme.primary)
        .clickable { onTodoClick(todoItem) }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {

        Checkbox(checked = isChecked!!, onCheckedChange = {
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
                color = if (isChecked!!) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f) else MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = todoItem.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = if (isChecked!!) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f) else MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TodoHomeScreenContentPreview() {
    //TodoHomeScreenContent(todoList = UiState.Loading, onEventSent = {}) {}
    //TodoListItem(TodoResource(), {}, {})
}

