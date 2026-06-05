package com.wonjo.deepfine.auth.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import com.wonjo.deepfine.auth.AuthContract.Event
import com.wonjo.deepfine.auth.AuthContract.UiState
import com.wonjo.deepfine.auth.R
import com.wonjo.deepfine.core.designsystem.AppTextStyles
import com.wonjo.deepfine.core.designsystem.Colors
import com.wonjo.deepfine.core.ui.BasicButton
import kotlinx.serialization.Serializable

@Serializable
internal data object AuthCompleteNavKey : NavKey

@Composable
internal fun AuthCompleteScreen(
    uiState: UiState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpScreenScaffold(
        titleContent = {
            Text(
                text = stringResource(R.string.auth_complete_name, uiState.name),
                style = AppTextStyles.ScreenTitle.copy(color = Colors.Primary),
            )
            Text(
                text = stringResource(R.string.auth_complete_title),
                style = AppTextStyles.ScreenTitle.copy(color = Colors.Black),
            )
        },
        description = stringResource(R.string.auth_complete_description),
        progress = 1f,
        bottomContent = {
            BasicButton(
                text = stringResource(R.string.auth_login_submit),
                onClick = { onEvent(Event.CompleteLoginClicked) },
            )
        },
        modifier = modifier,
    )
}
