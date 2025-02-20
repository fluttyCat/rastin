package com.rastin.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.rastin.android.navigation.RastinNavHost

@Composable
fun RastinApp(
    windowSizeClass: WindowSizeClass,
    appState: RastinAppState = rememberRastinAppState(
        windowSizeClass = windowSizeClass
    ),
    startDestination: String,
) {

    RastinAppContent(
        appState = appState,
        startDestination = startDestination
    )
}

@OptIn(
    ExperimentalComposeUiApi::class,
)
@Composable
private fun RastinAppContent(
    appState: RastinAppState,
    startDestination: String,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.semantics { testTagsAsResourceId = true }.background(Color.White),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {},
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {}
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            RastinNavHost(
                navController = appState.navController,
                onBackClick = appState::onBackClick,
                snackbarHostState = snackbarHostState,
                startDestination = startDestination,
                modifier = Modifier
                    .padding(padding)
                    .safeDrawingPadding()
            )
        }
    }
}