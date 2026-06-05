package com.wonjo.deepfine.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wonjo.deepfine.auth.AuthContract.Effect
import com.wonjo.deepfine.auth.AuthContract.EmailValidation
import com.wonjo.deepfine.auth.AuthContract.EmptyText
import com.wonjo.deepfine.auth.AuthContract.Event
import com.wonjo.deepfine.auth.AuthContract.UiMessage
import com.wonjo.deepfine.auth.AuthContract.UiState
import com.wonjo.deepfine.auth.AuthContract.NameValidation
import com.wonjo.deepfine.auth.AuthContract.PasswordValidation
import com.wonjo.deepfine.core.domain.auth.usecase.FindUserByEmailUseCase
import com.wonjo.deepfine.core.domain.auth.usecase.LoginUseCase
import com.wonjo.deepfine.core.domain.auth.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val findUserByEmailUseCase: FindUserByEmailUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {
    private val mutableUiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = mutableUiState.asStateFlow()

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    fun onEvent(event: Event) {
        when (event) {
            is Event.EmailChanged -> updateEmail(event.email)
            Event.ClearEmailClicked -> clearEmail()
            is Event.NameChanged -> updateName(event.name)
            Event.ClearNameClicked -> clearName()
            is Event.PasswordChanged -> updatePassword(event.password)
            Event.ClearPasswordClicked -> clearPassword()
            Event.SubmitLoginEmailClicked -> submitLoginEmail()
            Event.SubmitLoginPasswordClicked -> submitLoginPassword()
            Event.SubmitLoginSignUpClicked -> submitLoginSignUp()
            Event.SubmitNameClicked -> submitName()
            Event.SubmitPasswordClicked -> submitPassword()
            Event.CompleteLoginClicked -> completeLogin()
        }
    }

    private fun updateEmail(email: String) {
        mutableUiState.update { state ->
            state.copy(
                email = email,
                password = EmptyText,
                emailValidation = EmailValidation.Idle,
                passwordValidation = PasswordValidation.Idle,
            )
        }
    }

    private fun clearEmail() {
        mutableUiState.update { state ->
            state.copy(
                email = EmptyText,
                password = EmptyText,
                emailValidation = EmailValidation.Idle,
                passwordValidation = PasswordValidation.Idle,
            )
        }
    }

    private fun updateName(name: String) {
        mutableUiState.update { state ->
            state.copy(
                name = name,
                nameValidation = NameValidation.Idle,
            )
        }
    }

    private fun clearName() {
        mutableUiState.update { state ->
            state.copy(
                name = EmptyText,
                nameValidation = NameValidation.Idle,
            )
        }
    }

    private fun updatePassword(password: String) {
        mutableUiState.update { state ->
            state.copy(
                password = password,
                passwordValidation = PasswordValidation.Idle,
            )
        }
    }

    private fun clearPassword() {
        mutableUiState.update { state ->
            state.copy(
                password = EmptyText,
                passwordValidation = PasswordValidation.Idle,
            )
        }
    }

    private fun submitLoginEmail() {
        if (!validateEmail()) {
            return
        }

        viewModelScope.launch {
            mutableUiState.update { it.copy(isLoading = true) }
            val user = findUserByEmailUseCase(uiState.value.email)
            mutableUiState.update { state ->
                state.copy(
                    password = EmptyText,
                    isLoading = false,
                )
            }
            sendEffect(
                if (user == null) {
                    Effect.NavigateToLoginSignUp
                } else {
                    Effect.NavigateToLoginPassword
                },
            )
        }
    }

    private fun submitLoginPassword() {
        if (uiState.value.password.isBlank()) {
            return
        }

        viewModelScope.launch {
            mutableUiState.update { it.copy(isLoading = true) }
            val isMatched = loginUseCase(
                email = uiState.value.email,
                password = uiState.value.password,
            )
            mutableUiState.update { it.copy(isLoading = false) }
            sendEffect(
                Effect.ShowToast(
                    if (isMatched) {
                        UiMessage.LoginSuccess
                    } else {
                        UiMessage.WrongPassword
                    },
                ),
            )
        }
    }

    private fun submitLoginSignUp() {
        if (validateEmail()) {
            sendEffect(Effect.NavigateToName)
        }
    }

    private fun submitName() {
        val name = uiState.value.name.trim()
        if (name.isBlank()) {
            mutableUiState.update { state ->
                state.copy(nameValidation = NameValidation.Empty)
            }
            return
        }

        mutableUiState.update { state ->
            state.copy(
                name = name,
                nameValidation = NameValidation.Valid,
            )
        }
        sendEffect(Effect.NavigateToPassword)
    }

    private fun submitPassword() {
        if (!validateSignUpPassword()) {
            sendEffect(Effect.ShowToast(UiMessage.PasswordTooShort))
            return
        }

        viewModelScope.launch {
            mutableUiState.update { it.copy(isLoading = true) }
            saveUserUseCase(
                email = uiState.value.email,
                name = uiState.value.name,
                password = uiState.value.password,
            )
            mutableUiState.update { it.copy(isLoading = false) }
            sendEffect(Effect.NavigateToComplete)
        }
    }

    private fun completeLogin() {
        mutableUiState.update { UiState() }
        sendEffect(Effect.NavigateToLoginEmail)
    }

    private fun validateEmail(): Boolean {
        val email = uiState.value.email.trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mutableUiState.update { state ->
                state.copy(emailValidation = EmailValidation.InvalidFormat)
            }
            return false
        }

        mutableUiState.update { state ->
            state.copy(
                email = email,
                emailValidation = EmailValidation.Valid,
            )
        }
        return true
    }

    private fun validateSignUpPassword(): Boolean {
        if (!uiState.value.password.isValidSignUpPassword()) {
            mutableUiState.update { state ->
                state.copy(passwordValidation = PasswordValidation.TooShort)
            }
            return false
        }

        mutableUiState.update { state ->
            state.copy(passwordValidation = PasswordValidation.Valid)
        }
        return true
    }

    private fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            effectChannel.send(effect)
        }
    }
}

private const val PasswordMinLength = 8

private fun String.isValidSignUpPassword(): Boolean {
    if (length < 8) {
        return false
    }

    val categoryCount = listOf(
        any(Char::isUpperCase),
        any(Char::isLowerCase),
        any(Char::isDigit),
        any { !it.isLetterOrDigit() },
    ).count { it }

    return categoryCount >= 3
}
