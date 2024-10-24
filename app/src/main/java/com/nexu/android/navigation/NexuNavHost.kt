package com.nexu.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.nexu.feature.todohome.navigation.addTodoScreen
import com.nexu.feature.todohome.navigation.navigateToAddTodo
import com.nexu.feature.todohome.navigation.todoHomeScreen

@Composable
fun NexuNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        todoHomeScreen(navController::navigateToAddTodo)
        addTodoScreen(navigateBack = { navController.popBackStack() })
    }
}

fun popUpToTop(navController: NavController) = navOptions {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return@navOptions) {
        inclusive = true
    }
}
