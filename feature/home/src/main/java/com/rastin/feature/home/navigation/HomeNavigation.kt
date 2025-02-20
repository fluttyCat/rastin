package com.rastin.feature.home.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rastin.feature.home.HomeScreen


const val homeRoute = "homeRoute"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(snackbarHostState: SnackbarHostState, navigateBack: () -> Unit) {
    composable(
        route = homeRoute,
    ) {
        HomeScreen()
    }
}