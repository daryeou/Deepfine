package com.wonjo.deepfine.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField as ComposeBasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.wonjo.deepfine.core.designsystem.AppTextStyles
import com.wonjo.deepfine.core.designsystem.Colors
import com.wonjo.deepfine.core.ui.atomic.VerticalSpacer
import kotlinx.coroutines.launch

private enum class InputStatus(val themeColor: Color) {
    Normal(themeColor = Colors.GrayL3),
    Active(themeColor = Colors.Primary),
    Error(themeColor = Colors.Error),
}

@Composable
fun BasicTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    label: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    clearIconContentDescription: String? = null,
    onClear: (() -> Unit)? = { onValueChange("") },
    onFocusChanged: (Boolean) -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val inputStatus = when {
        isError -> InputStatus.Error
        isFocused || text.isNotEmpty() -> InputStatus.Active
        else -> InputStatus.Normal
    }
    val fieldTextStyle = AppTextStyles.TextFieldInput.copy(
        color = Colors.Black,
    )
    val clearButtonContentDescription =
        clearIconContentDescription ?: stringResource(R.string.core_ui_clear_text)

    Column(
        modifier = modifier.bringIntoViewRequester(bringIntoViewRequester),
    ) {
        label?.let {
            Text(
                text = it,
                style = AppTextStyles.TextFieldLabel.copy(
                    color = Colors.Black,
                ),
                minLines = 1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            VerticalSpacer(height = 7.dp)
        }

        ComposeBasicTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                    onFocusChanged(it.isFocused)
                    if (it.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            readOnly = readOnly,
            singleLine = true,
            minLines = 1,
            maxLines = 1,
            textStyle = fieldTextStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(inputStatus.themeColor),
            decorationBox = { innerTextField ->
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (text.isEmpty() && hint.isNotEmpty()) {
                                Text(
                                    text = hint,
                                    style = fieldTextStyle.copy(color = Colors.GrayM),
                                    minLines = 1,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            innerTextField()
                        }

                        if (!readOnly && text.isNotEmpty() && onClear != null) {
                            ClearButton(
                                contentDescription = clearButtonContentDescription,
                                onClick = onClear,
                            )
                        }
                    }

                    VerticalSpacer(height = 14.dp)

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(inputStatus.themeColor),
                    )
                }
            },
        )

        VerticalSpacer(height = 6.dp)
        Text(
            text = supportingText ?: "",
            style = AppTextStyles.TextFieldSupporting.copy(
                color = if (isError) Colors.Error else Colors.Primary,
            ),
            minLines = 1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun ClearButton(
    contentDescription: String?,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
            .size(20.dp)
            .clickable(
                role = Role.Button,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.cancel),
            contentDescription = contentDescription,
            modifier = Modifier.size(16.dp),
            tint = Colors.GrayL4,
        )
    }
}
