package com.nexu.android.core.ui

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexu.android.core.designsystem.component.NexuAnimation

@Composable
fun NexuLoading(
    modifier: Modifier,
    @RawRes jsonRes: Int = com.nexu.android.core.designsystem.R.raw.loading,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NexuAnimation(jsonRes = jsonRes, modifier = Modifier.size(240.dp))
    }
}