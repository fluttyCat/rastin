package com.nexu.feature.todohome

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddTodoScreen(
    onAddTodo: (String, String, Boolean) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isCompleted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title Input
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description Input
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        /*// Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = { isCompleted = it }
            )
            Text(text = "Completed")
        }*/

        Spacer(modifier = Modifier.height(32.dp))

        // Add Todo Button
        Button(
            onClick = {
                onAddTodo(title, description, isCompleted)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Add Todo")
        }
    }
}

@Preview
@Composable
fun PreviewAddTodoScreen() {
    AddTodoScreen(onAddTodo = { title, description, isCompleted ->
        // Handle the new TODO here
    })
}

