package com.rastin.android.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.rastin.feature.home.navigation.homeScreen

@Composable
fun RastinNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    startDestination: String,
    onBackClick: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(snackbarHostState, onBackClick)
    }
}

fun popUpToTop(navController: NavController) = navOptions {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return@navOptions) {
        inclusive = true
    }
}
