package com.nexu.feature.todohome.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nexu.android.core.data.model.TodoResource
import com.nexu.android.core.designsystem.theme.NexuBackground
import com.nexu.android.core.designsystem.theme.NexuTheme

@Composable
internal fun TodoItem(todo: TodoResource) {

    val expanded = rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = todo.title ?: "",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium
                    )


                    Spacer(modifier = Modifier.height(8.dp))

                }

            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PreviewTodoItem() {
    NexuTheme {
        NexuBackground {
            LazyColumn {
                item {
                    TodoItem(TodoResource())
                }
            }
        }
    }
}