package com.wonjo.deepfine.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.wonjo.deepfine.auth.component.clearFocusOnTap
import com.wonjo.deepfine.core.designsystem.AppTextStyles
import com.wonjo.deepfine.core.designsystem.Colors
import com.wonjo.deepfine.core.ui.BasicButton
import com.wonjo.deepfine.core.ui.atomic.VerticalSpacer

@Composable
internal fun LoginScreenLayout(
    title: String,
    buttonText: String,
    buttonEnabled: Boolean,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    isLoading: Boolean = false,
    content: @Composable () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .clearFocusOnTap(focusManager)
            .safeDrawingPadding()
            .padding(horizontal = 24.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            VerticalSpacer(height = 116.dp)
            Text(
                text = title,
                style = AppTextStyles.ScreenTitle.copy(color = Colors.Black),
            )
            description?.let {
                VerticalSpacer(height = 10.dp)
                Text(
                    text = it,
                    style = AppTextStyles.ScreenDescription.copy(color = Colors.GrayM),
                )
            }
            VerticalSpacer(height = 40.dp)
            content()
        }
        BasicButton(
            text = buttonText,
            enabled = buttonEnabled,
            isLoading = isLoading,
            onClick = onButtonClick,
            modifier = Modifier.padding(bottom = 40.dp),
        )
    }
}
