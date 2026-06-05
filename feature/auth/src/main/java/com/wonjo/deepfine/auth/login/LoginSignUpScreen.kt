package com.wonjo.deepfine.auth.login

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation3.runtime.NavKey
import com.wonjo.deepfine.auth.AuthContract.Event
import com.wonjo.deepfine.auth.AuthContract.UiState
import com.wonjo.deepfine.auth.R
import com.wonjo.deepfine.core.ui.BasicTextField
import kotlinx.serialization.Serializable

@Serializable
internal data object LoginSignUpNavKey : NavKey

@Composable
internal fun LoginSignUpScreen(
    uiState: UiState,
    onEvent: (Event) -> Unit,
    onEmailEdited: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LoginScreenLayout(
        title = stringResource(R.string.auth_sign_up_title),
        buttonText = stringResource(R.string.auth_next),
        buttonEnabled = uiState.email.isNotBlank(),
        isLoading = uiState.isLoading,
        onButtonClick = { onEvent(Event.SubmitLoginSignUpClicked) },
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
            supportingText = stringResource(R.string.auth_login_email_not_found),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onEvent(Event.SubmitLoginSignUpClicked) },
            ),
            onClear = {
                onEvent(Event.ClearEmailClicked)
                onEmailEdited()
            },
        )
    }
}
