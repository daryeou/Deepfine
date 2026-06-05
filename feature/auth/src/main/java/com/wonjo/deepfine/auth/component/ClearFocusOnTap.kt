package com.wonjo.deepfine.auth.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput

internal fun Modifier.clearFocusOnTap(focusManager: FocusManager): Modifier =
    pointerInput(focusManager) {
        detectTapGestures(
            onTap = {
                focusManager.clearFocus()
            },
        )
    }
