package com.wonjo.deepfine.auth

object AuthContract {
    data class UiState(
        val email: String = EmptyText,
        val name: String = EmptyText,
        val password: String = EmptyText,
        val emailValidation: EmailValidation = EmailValidation.Idle,
        val nameValidation: NameValidation = NameValidation.Idle,
        val passwordValidation: PasswordValidation = PasswordValidation.Idle,
        val isLoading: Boolean = false,
    )

    enum class EmailValidation {
        Idle,
        InvalidFormat,
        Valid,
    }

    enum class NameValidation {
        Idle,
        Empty,
        Valid,
    }

    enum class PasswordValidation {
        Idle,
        TooShort,
        Valid,
    }

    sealed interface Event {
        data class EmailChanged(val email: String) : Event
        data object ClearEmailClicked : Event
        data class NameChanged(val name: String) : Event
        data object ClearNameClicked : Event
        data class PasswordChanged(val password: String) : Event
        data object ClearPasswordClicked : Event
        data object SubmitLoginEmailClicked : Event
        data object SubmitLoginPasswordClicked : Event
        data object SubmitLoginSignUpClicked : Event
        data object SubmitNameClicked : Event
        data object SubmitPasswordClicked : Event
        data object CompleteLoginClicked : Event
    }

    sealed interface Effect {
        data object NavigateToLoginEmail : Effect
        data object NavigateToLoginPassword : Effect
        data object NavigateToLoginSignUp : Effect
        data object NavigateToName : Effect
        data object NavigateToPassword : Effect
        data object NavigateToComplete : Effect
        data class ShowToast(val message: UiMessage) : Effect
    }

    sealed interface UiMessage {
        data object LoginSuccess : UiMessage
        data object WrongPassword : UiMessage
        data object PasswordTooShort : UiMessage
    }

    const val EmptyText = ""
}
