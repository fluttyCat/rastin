package com.nexu.android.ui

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
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.nexu.android.core.designsystem.theme.NexuBackground
import com.nexu.android.navigation.NexuNavHost

@Composable
fun NexuApp(
    windowSizeClass: WindowSizeClass,
    appState: NexuAppState = rememberNexuAppState(
        windowSizeClass = windowSizeClass
    ),
    startDestination: String,
) {
    NexuBackground {
        NexuAppContent(
            appState = appState,
            startDestination = startDestination
        )
    }
}

@OptIn(
    ExperimentalComposeUiApi::class,
)
@Composable
private fun NexuAppContent(
    appState: NexuAppState,
    startDestination: String,
) {
    Scaffold(
        modifier = Modifier.semantics { testTagsAsResourceId = true },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {},
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
            NexuNavHost(
                navController = appState.navController,
                onBackClick = appState::onBackClick,
                startDestination = startDestination,
                modifier = Modifier
                    .padding(padding)
                    .safeDrawingPadding()
            )
        }
    }
}