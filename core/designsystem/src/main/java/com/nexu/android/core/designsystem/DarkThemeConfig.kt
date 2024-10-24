package com.nexu.android.core.designsystem

import androidx.annotation.StringRes
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.luminance
import com.nexu.android.core.data.model.DarkThemeConfig as DarkModel

enum class DarkThemeConfig(@StringRes val title: Int) {
    FOLLOW_SYSTEM(R.string.system_default),
    LIGHT(R.string.light),
    DARK(R.string.dark)
}

fun DarkThemeConfig.asModel(): DarkModel {
    return when (this) {
        DarkThemeConfig.FOLLOW_SYSTEM -> DarkModel.FOLLOW_SYSTEM
        DarkThemeConfig.LIGHT -> DarkModel.LIGHT
        DarkThemeConfig.DARK -> DarkModel.DARK
    }
}

fun com.nexu.android.core.data.model.DarkThemeConfig.asDesignModel(): DarkThemeConfig {
    return when (this) {
        com.nexu.android.core.data.model.DarkThemeConfig.FOLLOW_SYSTEM -> DarkThemeConfig.FOLLOW_SYSTEM
        com.nexu.android.core.data.model.DarkThemeConfig.LIGHT -> DarkThemeConfig.LIGHT
        com.nexu.android.core.data.model.DarkThemeConfig.DARK -> DarkThemeConfig.DARK
    }
}

@Composable
fun ColorScheme.isLight() = this.background.luminance() > 0.5