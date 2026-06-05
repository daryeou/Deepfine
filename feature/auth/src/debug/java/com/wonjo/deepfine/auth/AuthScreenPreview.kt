package com.wonjo.deepfine.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wonjo.deepfine.auth.AuthContract.EmailValidation
import com.wonjo.deepfine.auth.AuthContract.NameValidation
import com.wonjo.deepfine.auth.AuthContract.PasswordValidation
import com.wonjo.deepfine.auth.AuthContract.UiState
import com.wonjo.deepfine.auth.login.LoginEmailScreen
import com.wonjo.deepfine.auth.login.LoginPasswordScreen
import com.wonjo.deepfine.auth.login.LoginSignUpScreen
import com.wonjo.deepfine.auth.signup.AuthCompleteScreen
import com.wonjo.deepfine.auth.signup.NameInputScreen
import com.wonjo.deepfine.auth.signup.PasswordInputScreen
import com.wonjo.deepfine.core.designsystem.AppTheme

@Preview(
    name = "Auth login empty",
    showBackground = true,
    widthDp = 360,
    heightDp = 760,
)
@Composable
private fun AuthLoginEmptyPreview() {
    AuthPreviewSurface {
        LoginEmailScreen(
            uiState = UiState(),
            onEvent = {},
        )
    }
}

@Preview(
    name = "Auth login invalid",
    showBackground = true,
    widthDp = 360,
    heightDp = 760,
)
@Composable
private fun AuthLoginInvalidPreview() {
    AuthPreviewSurface {
        LoginEmailScreen(
            uiState = UiState(
                email = "android@deepfine",
                password = "password",
                emailValidation = EmailValidation.InvalidFormat,
            ),
            onEvent = {},
        )
    }
}

@Preview(
    name = "Auth sign up email",
    showBackground = true,
    widthDp = 360,
    heightDp = 760,
)
@Composable
private fun AuthSignUpEmailPreview() {
    AuthPreviewSurface {
        LoginSignUpScreen(
            uiState = UiState(
                email = "android@deepfine.com",
                emailValidation = EmailValidation.Valid,
            ),
            onEvent = {},
            onEmailEdited = {},
        )
    }
}

@Preview(
    name = "Auth login password",
    showBackground = true,
    widthDp = 360,
    heightDp = 760,
)
@Composable
private fun AuthLoginPasswordPreview() {
    AuthPreviewSurface {
        LoginPasswordScreen(
            uiState = UiState(
                email = "android@deepfine.com",
                emailValidation = EmailValidation.Valid,
            ),
            onEvent = {},
            onEmailEdited = {},
        )
    }
}

@Preview(
    name = "Auth name invalid",
    showBackground = true,
    widthDp = 360,
    heightDp = 760,
)
@Composable
private fun AuthNameInvalidPreview() {
    AuthPreviewSurface {
        NameInputScreen(
            uiState = UiState(
                email = "android@deepfine.com",
                emailValidation = EmailValidation.Valid,
                nameValidation = NameValidation.Empty,
            ),
            onEvent = {},
            onBackClick = {},
        )
    }
}

@Preview(
    name = "Auth password invalid",
    showBackground = true,
    widthDp = 360,
    heightDp = 760,
)
@Composable
private fun AuthPasswordInvalidPreview() {
    AuthPreviewSurface {
        PasswordInputScreen(
            uiState = UiState(
                email = "android@deepfine.com",
                name = "Android",
                password = "123",
                passwordValidation = PasswordValidation.TooShort,
            ),
            onEvent = {},
            onBackClick = {},
        )
    }
}

@Preview(
    name = "Auth complete",
    showBackground = true,
    widthDp = 360,
    heightDp = 760,
)
@Composable
private fun AuthCompletePreview() {
    AuthPreviewSurface {
        AuthCompleteScreen(
            uiState = UiState(name = "Android"),
            onEvent = {},
        )
    }
}

@Composable
private fun AuthPreviewSurface(
    content: @Composable () -> Unit,
) {
    AppTheme(content = content)
}
