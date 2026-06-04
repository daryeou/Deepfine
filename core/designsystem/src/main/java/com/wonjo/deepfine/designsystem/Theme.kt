package com.wonjo.deepfine.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        typography = AppTypography,
    ) {
        content()
    }
}