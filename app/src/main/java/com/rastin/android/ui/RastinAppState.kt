package com.rastin.android.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rastin.android.core.ui.TrackDisposableJank

@Composable
fun rememberRastinAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): RastinAppState {
    NavigationTrackingSideEffect(navController)
    return remember(navController, windowSizeClass) {
        RastinAppState(
            navController,
            windowSizeClass,
        )
    }
}

@Stable
class RastinAppState(
    val navController: NavHostController,
    private val windowSizeClass: WindowSizeClass,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun onBackClick() {
        navController.popBackStack()
    }
}

@Composable
private fun NavigationTrackingSideEffect(
    navController: NavHostController,
) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
