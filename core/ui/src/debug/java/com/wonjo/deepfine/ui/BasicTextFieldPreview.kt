package com.wonjo.deepfine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wonjo.deepfine.designsystem.AppTheme
import com.wonjo.deepfine.designsystem.Colors

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
                supportingText = "이메일 형식이 올바르지 않습니다.",
                isError = true,
            )
            EmailPreviewCase(text = "zuel2123@deepfine.com")
        }
    }
}

@Composable
private fun EmailPreviewCase(
    text: String,
    supportingText: String? = null,
    isError: Boolean = false,
) {
    BasicTextField(
        text = text,
        onValueChange = {},
        hint = "이메일 주소를 입력하세요.",
        supportingText = supportingText,
        isError = isError,
        onClear = {},
    )
}
