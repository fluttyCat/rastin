package com.nexu.feature.todohome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexu.feature.todohome.AddTodoScreen
import com.nexu.feature.todohome.TodoHomeScreen


const val todoHomeRoute = "todoHomeRoute"
const val addTodoRoute = "addTodoRoute"

fun NavController.navigateToTodoHome(
    navOptions: NavOptions? = null
) {
    this.navigate(todoHomeRoute, navOptions)
}

fun NavGraphBuilder.todoHomeScreen(navigateToAddTodo: () -> Unit) {
    composable(
        route = todoHomeRoute,
    ) {
        TodoHomeScreen(navigateToAddTodo = navigateToAddTodo)
    }
}

fun NavController.navigateToAddTodo(
    navOptions: NavOptions? = null
) {
    this.navigate(addTodoRoute, navOptions)
}

fun NavGraphBuilder.addTodoScreen(navigateBack: () -> Unit) {
    composable(
        route = addTodoRoute,
    ) {
        AddTodoScreen(
            onNavigateBack = navigateBack,
            onAddTodo = { title, description, isCompleted -> }) { }
    }
}