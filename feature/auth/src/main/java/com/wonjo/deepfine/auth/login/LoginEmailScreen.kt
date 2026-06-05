package com.wonjo.deepfine.auth.login

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation3.runtime.NavKey
import com.wonjo.deepfine.auth.AuthContract
import com.wonjo.deepfine.auth.AuthContract.Event
import com.wonjo.deepfine.auth.AuthContract.UiState
import com.wonjo.deepfine.auth.R
import com.wonjo.deepfine.auth.errorMessage
import com.wonjo.deepfine.core.ui.BasicTextField
import kotlinx.serialization.Serializable

@Serializable
internal data object LoginEmailNavKey : NavKey

@Composable
internal fun LoginEmailScreen(
    uiState: UiState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    LoginScreenLayout(
        title = stringResource(R.string.auth_login_email_title),
        description = stringResource(R.string.auth_login_email_description),
        buttonText = stringResource(R.string.auth_login_email_submit),
        buttonEnabled = uiState.email.isNotBlank(),
        isLoading = uiState.isLoading,
        onButtonClick = { onEvent(Event.SubmitLoginEmailClicked) },
        modifier = modifier,
    ) {
        BasicTextField(
            text = uiState.email,
            onValueChange = { onEvent(Event.EmailChanged(it)) },
            label = stringResource(R.string.auth_email_label),
            hint = stringResource(R.string.auth_email_hint),
            supportingText = uiState.emailValidation.errorMessage(),
            isError = uiState.emailValidation == AuthContract.EmailValidation.InvalidFormat,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onEvent(Event.SubmitLoginEmailClicked) },
            ),
            onClear = { onEvent(Event.ClearEmailClicked) },
        )
    }
}
