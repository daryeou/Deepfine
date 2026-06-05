package com.wonjo.deepfine.auth.signup

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
import com.wonjo.deepfine.core.ui.BasicButton
import com.wonjo.deepfine.core.ui.BasicTextField
import kotlinx.serialization.Serializable

@Serializable
internal data object NameInputNavKey : NavKey

@Composable
internal fun NameInputScreen(
    uiState: UiState,
    onEvent: (Event) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpScreenScaffold(
        title = stringResource(R.string.auth_name_title),
        description = stringResource(R.string.auth_name_description),
        progress = 0.3f,
        bottomContent = {
            BasicButton(
                text = stringResource(R.string.auth_next),
                enabled = uiState.name.isNotBlank(),
                isLoading = uiState.isLoading,
                onClick = { onEvent(Event.SubmitNameClicked) },
            )
        },
        modifier = modifier,
        onBackClick = onBackClick,
    ) {
        BasicTextField(
            text = uiState.name,
            onValueChange = { onEvent(Event.NameChanged(it)) },
            label = stringResource(R.string.auth_name_label),
            hint = stringResource(R.string.auth_name_hint),
            supportingText = uiState.nameValidation.errorMessage(),
            isError = uiState.nameValidation == AuthContract.NameValidation.Empty,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onEvent(Event.SubmitNameClicked) },
            ),
            onClear = { onEvent(Event.ClearNameClicked) },
        )
    }
}

private const val SignUpNameProgress = 0.5f
