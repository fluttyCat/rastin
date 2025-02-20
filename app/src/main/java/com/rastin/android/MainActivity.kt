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
import com.rastin.android.core.designsystem.theme.NexuTheme
import com.nexu.android.ui.NexuApp
import com.rastin.feature.todohome.navigation.todoHomeRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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

        var splashVisible = true
        lifecycleScope.launch {
            delay(500L)
            splashVisible = false
        }

        splashScreen.setKeepOnScreenCondition {
            splashVisible
        }
        enableEdgeToEdge()

        setContent {
            NexuTheme {
                NexuApp(
                    windowSizeClass = calculateWindowSizeClass(this),
                    startDestination = detectStartDestination()
                )
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
): String = todoHomeRoute