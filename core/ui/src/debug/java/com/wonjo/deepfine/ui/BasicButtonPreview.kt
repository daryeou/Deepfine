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
    name = "Basic button cases",
    showBackground = true,
    widthDp = 360,
)
@Composable
private fun BasicButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Colors.White)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            BasicButton(
                text = "로그인/회원가입",
                onClick = {},
            )
            BasicButton(
                text = "로그인/회원가입",
                onClick = {},
                enabled = false,
            )
            BasicButton(
                text = "로그인/회원가입",
                onClick = {},
                isLoading = true,
            )
        }
    }
}
