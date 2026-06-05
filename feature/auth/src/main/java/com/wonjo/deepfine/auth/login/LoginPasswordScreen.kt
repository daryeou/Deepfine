package com.wonjo.deepfine.auth.login

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.wonjo.deepfine.auth.AuthContract.Event
import com.wonjo.deepfine.auth.AuthContract.UiState
import com.wonjo.deepfine.auth.R
import com.wonjo.deepfine.core.ui.BasicTextField
import com.wonjo.deepfine.core.ui.atomic.VerticalSpacer
import kotlinx.serialization.Serializable

@Serializable
internal data object LoginPasswordNavKey : NavKey

@Composable
internal fun LoginPasswordScreen(
    uiState: UiState,
    onEvent: (Event) -> Unit,
    onEmailEdited: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LoginScreenLayout(
        title = stringResource(R.string.auth_login_title),
        description = stringResource(R.string.auth_login_password_description),
        buttonText = stringResource(R.string.auth_login_submit),
        buttonEnabled = uiState.password.isNotBlank(),
        isLoading = uiState.isLoading,
        onButtonClick = { onEvent(Event.SubmitLoginPasswordClicked) },
        modifier = modifier,
    ) {
        BasicTextField(
            text = uiState.email,
            onValueChange = {
                onEvent(Event.EmailChanged(it))
                onEmailEdited()
            },
            label = stringResource(R.string.auth_email_label),
            hint = stringResource(R.string.auth_email_hint),
            supportingText = stringResource(R.string.auth_login_email_confirmed),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            onClear = {
                onEvent(Event.ClearEmailClicked)
                onEmailEdited()
            },
        )
        VerticalSpacer(height = 24.dp)
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
                onDone = { onEvent(Event.SubmitLoginPasswordClicked) },
            ),
            visualTransformation = PasswordVisualTransformation(),
            onClear = null,
        )
    }
}
