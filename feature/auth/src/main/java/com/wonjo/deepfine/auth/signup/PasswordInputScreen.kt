package com.wonjo.deepfine.auth.signup

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation3.runtime.NavKey
import com.wonjo.deepfine.auth.AuthContract.Event
import com.wonjo.deepfine.auth.AuthContract.UiState
import com.wonjo.deepfine.auth.R
import com.wonjo.deepfine.core.ui.BasicButton
import com.wonjo.deepfine.core.ui.BasicTextField
import kotlinx.serialization.Serializable

@Serializable
internal data object PasswordInputNavKey : NavKey

@Composable
internal fun PasswordInputScreen(
    uiState: UiState,
    onEvent: (Event) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpScreenScaffold(
        title = stringResource(R.string.auth_password_title),
        description = stringResource(R.string.auth_password_description),
        progress = 0.6f,
        bottomContent = {
            BasicButton(
                text = stringResource(R.string.auth_sign_up_complete),
                enabled = uiState.password.isNotBlank(),
                isLoading = uiState.isLoading,
                onClick = { onEvent(Event.SubmitPasswordClicked) },
            )
        },
        modifier = modifier,
        onBackClick = onBackClick,
    ) {
        BasicTextField(
            text = uiState.password,
            onValueChange = { onEvent(Event.PasswordChanged(it)) },
            label = stringResource(R.string.auth_password_label),
            hint = stringResource(R.string.auth_password_hint),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onEvent(Event.SubmitPasswordClicked) },
            ),
            visualTransformation = PasswordVisualTransformation(),
            onClear = null,
        )
    }
}

private const val SignUpPasswordProgress = 0.75f
