package com.nexu.feature.todohome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexu.feature.todohome.TodoHomeScreen


const val todoHomeRoute = "todoHomeRoute"
const val addTodoRoute = "addTodoRoute"

fun NavController.navigateToTodoHome(
    navOptions: NavOptions? = null
) {
    this.navigate(todoHomeRoute, navOptions)
}

fun NavGraphBuilder.todoHomeScreen() {
    composable(
        route = todoHomeRoute,
    ) {
        TodoHomeScreen()
    }
}

fun NavController.navigateToAddTodo(
    navOptions: NavOptions? = null
) {
    this.navigate(addTodoRoute, navOptions)
}

fun NavGraphBuilder.addTodoScreen() {
    composable(
        route = addTodoRoute,
    ) {
        TodoHomeScreen()
    }
}