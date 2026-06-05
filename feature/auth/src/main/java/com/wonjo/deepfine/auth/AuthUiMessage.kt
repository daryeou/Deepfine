package com.wonjo.deepfine.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

@Composable
internal fun AuthContract.EmailValidation.errorMessage(): String? = when (this) {
    AuthContract.EmailValidation.Idle,
    AuthContract.EmailValidation.Valid,
    -> null
    AuthContract.EmailValidation.InvalidFormat -> stringResource(R.string.auth_email_invalid_format)
}

@Composable
internal fun AuthContract.NameValidation.errorMessage(): String? = when (this) {
    AuthContract.NameValidation.Idle,
    AuthContract.NameValidation.Valid,
    -> null
    AuthContract.NameValidation.Empty -> stringResource(R.string.auth_name_required)
}

@Composable
internal fun AuthContract.PasswordValidation.errorMessage(): String? = when (this) {
    AuthContract.PasswordValidation.Idle,
    AuthContract.PasswordValidation.Valid,
    -> null
    AuthContract.PasswordValidation.TooShort -> stringResource(R.string.auth_password_invalid_format)
}

@Composable
internal fun AuthContract.UiMessage.asString(): String = when (this) {
    AuthContract.UiMessage.LoginSuccess -> stringResource(R.string.auth_login_success)
    AuthContract.UiMessage.WrongPassword -> stringResource(R.string.auth_wrong_password)
    AuthContract.UiMessage.PasswordTooShort -> stringResource(R.string.auth_password_invalid_format)
}

@Composable
internal fun rememberAuthUiMessageResolver(): (AuthContract.UiMessage) -> String {
    val loginSuccess = stringResource(R.string.auth_login_success)
    val wrongPassword = stringResource(R.string.auth_wrong_password)
    val passwordInvalidFormat = stringResource(R.string.auth_password_invalid_format)

    return remember(loginSuccess, wrongPassword, passwordInvalidFormat) {
        { message ->
            when (message) {
                AuthContract.UiMessage.LoginSuccess -> loginSuccess
                AuthContract.UiMessage.WrongPassword -> wrongPassword
                AuthContract.UiMessage.PasswordTooShort -> passwordInvalidFormat
            }
        }
    }
}
