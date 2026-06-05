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
    name = "Email text field cases",
    showBackground = true,
    widthDp = 360,
    heightDp = 760,
)
@Composable
private fun BasicTextFieldEmailPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Colors.White)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(42.dp),
        ) {
            EmailPreviewCase(text = "")
            EmailPreviewCase(
                text = "",
                isError = true,
            )
            EmailPreviewCase(text = "android@deepfine.com")
            EmailPreviewCase(
                text = "android@deepfine",
                supportingText = stringResource(R.string.core_ui_preview_email_invalid_format),
                isError = true,
            )
            EmailPreviewCase(
                text = "zuel2123@deepfine.com",
                readOnly = true,
            )
        }
    }
}

@Composable
private fun EmailPreviewCase(
    text: String,
    supportingText: String? = null,
    isError: Boolean = false,
    readOnly: Boolean = false,
) {
    BasicTextField(
        text = text,
        onValueChange = {},
        hint = stringResource(R.string.core_ui_preview_email_hint),
        supportingText = supportingText,
        isError = isError,
        readOnly = readOnly,
        onClear = {},
    )
}
