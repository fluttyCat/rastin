package com.nexu.android.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nexu.android.core.designsystem.component.NexuFilledButton
import com.nexu.android.core.designsystem.theme.NexuBackground
import com.nexu.android.core.designsystem.theme.NexuTheme

@Composable
fun HafthashtadFailure(
    modifier: Modifier = Modifier,
    message: String,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {

        Text(
            text = message,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        NexuFilledButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                onRefreshClick()
            },
            text = {
                Text(
                    text = stringResource(id = com.nexu.android.core.designsystem.R.string.retry),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.background
                )
            }
        )
    }
}


@Preview(
    name = "FailureOrEmptyHafthashtad",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "FailureOrEmptyHafthashtad",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewHafthashtadFailure() {
    NexuTheme {
        NexuBackground {
            HafthashtadFailure(Modifier, message = "error message") {}
        }
    }
}
