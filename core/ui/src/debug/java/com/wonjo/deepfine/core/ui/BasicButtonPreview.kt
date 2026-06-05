package com.wonjo.deepfine.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wonjo.deepfine.core.designsystem.AppTheme
import com.wonjo.deepfine.core.designsystem.Colors

@Preview(
    name = "Basic button cases",
    showBackground = true,
    widthDp = 360,
)
@Composable
private fun BasicButtonPreview() {
    AppTheme {
        val buttonText = stringResource(R.string.core_ui_preview_auth_button)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Colors.White)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            BasicButton(
                text = buttonText,
                onClick = {},
            )
            BasicButton(
                text = buttonText,
                onClick = {},
                enabled = false,
            )
            BasicButton(
                text = buttonText,
                onClick = {},
                isLoading = true,
            )
        }
    }
}
