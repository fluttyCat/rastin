package com.nexu.android.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.nexu.android.core.designsystem.R

/**
 * Nexu icons. Material icons are [ImageVector]s, custom icons are drawable resource IDs.
 */
object NexuIcons {
    val Circle = Icons.Rounded.Circle

    val Back = R.drawable.arrow_back_24px

    val Arrow = R.drawable.arrow

    val KeyboardArrowUp = Icons.Rounded.KeyboardArrowUp
    val KeyboardArrowDown = Icons.Rounded.KeyboardArrowDown

    val Add = R.drawable.add_24px
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class NexuIcon {
    data class ImageVectorIcon(val imageVector: ImageVector) : NexuIcon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : NexuIcon()
}
