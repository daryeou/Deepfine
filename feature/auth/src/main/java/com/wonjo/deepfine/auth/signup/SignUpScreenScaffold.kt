package com.wonjo.deepfine.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.wonjo.deepfine.auth.R
import com.wonjo.deepfine.auth.component.clearFocusOnTap
import com.wonjo.deepfine.core.designsystem.AppTextStyles
import com.wonjo.deepfine.core.designsystem.Colors
import com.wonjo.deepfine.core.ui.atomic.VerticalSpacer

@Composable
internal fun SignUpScreenScaffold(
    title: String,
    description: String,
    progress: Float,
    bottomContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    content: @Composable () -> Unit = {},
) {
    SignUpScreenScaffold(
        titleContent = {
            Text(
                text = title,
                style = AppTextStyles.ScreenTitle.copy(color = Colors.Black),
            )
        },
        description = description,
        bottomContent = bottomContent,
        modifier = modifier,
        onBackClick = onBackClick,
        progress = progress,
        content = content,
    )
}

@Composable
internal fun SignUpScreenScaffold(
    titleContent: @Composable () -> Unit,
    description: String,
    progress: Float,
    bottomContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    content: @Composable () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .clearFocusOnTap(focusManager)
            .safeDrawingPadding(),
    ) {
        VerticalSpacer(24.dp)

        AuthTopBar(
            onBackClick = onBackClick,
            progress = progress,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
            ) {
                VerticalSpacer(height = 36.dp)
                titleContent()
                if (description.isNotBlank()) {
                    VerticalSpacer(height = 12.dp)
                    Text(
                        text = description,
                        style = AppTextStyles.ScreenDescription.copy(color = Colors.GrayM),
                    )
                }
                VerticalSpacer(height = 40.dp)
                content()
            }
            Box(modifier = Modifier.padding(bottom = 40.dp)) {
                bottomContent()
            }
        }
    }
}

@Composable
private fun AuthTopBar(
    onBackClick: (() -> Unit)?,
    progress: Float,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
    ) {
        BackButton(
            onClick = onBackClick ?: {},
            modifier = Modifier
                .alpha(if (onBackClick != null) 1f else 0f)
                .padding(start = 24.dp),
        )

        VerticalSpacer(12.dp)

        ProgressLine(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backContentDescription = stringResource(R.string.auth_back_content_description)

    Box(
        modifier = modifier
            .size(32.dp)
            .semantics {
                contentDescription = backContentDescription
            }
            .clickable(
                role = Role.Button,
                onClick = onClick,
            ),
        contentAlignment = Alignment.CenterStart,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow_back),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = Colors.Black,
        )
    }
}

@Composable
private fun ProgressLine(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(5.dp)
            .background(Colors.GrayL5),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .fillMaxHeight()
                .clip(RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp))
                .background(Colors.Primary),
        )
    }
}
