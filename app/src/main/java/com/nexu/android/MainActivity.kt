package com.nexu.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.metrics.performance.JankStats
import com.nexu.android.MainActivityUiState.Loading
import com.nexu.android.MainActivityUiState.Success
import com.nexu.android.core.designsystem.theme.NexuTheme
import com.nexu.android.ui.NexuApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var lazyStats: dagger.Lazy<JankStats>

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        lazyStats.get().isTrackingEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(Loading)

        lifecycleScope.launch {
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                is Loading -> true
                else -> false
            }
        }

        enableEdgeToEdge()

        setContent {


            if (uiState != Loading) {
                NexuTheme {
                    NexuApp(
                        windowSizeClass = calculateWindowSizeClass(this),
                        startDestination = detectStartDestination(uiState as Success)
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        lazyStats.get().isTrackingEnabled = false
    }

}

@Composable
fun detectStartDestination(
    uiState: Success,
): String = "todoRoute"


private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

