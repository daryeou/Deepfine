package com.wonjo.deepfine.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.Scene
import androidx.navigation3.ui.NavDisplay
import com.wonjo.deepfine.auth.AuthContract.Effect
import com.wonjo.deepfine.auth.login.LoginEmailNavKey
import com.wonjo.deepfine.auth.login.LoginEmailScreen
import com.wonjo.deepfine.auth.login.LoginPasswordNavKey
import com.wonjo.deepfine.auth.login.LoginPasswordScreen
import com.wonjo.deepfine.auth.login.LoginSignUpNavKey
import com.wonjo.deepfine.auth.login.LoginSignUpScreen
import com.wonjo.deepfine.auth.signup.AuthCompleteNavKey
import com.wonjo.deepfine.auth.signup.AuthCompleteScreen
import com.wonjo.deepfine.auth.signup.NameInputNavKey
import com.wonjo.deepfine.auth.signup.NameInputScreen
import com.wonjo.deepfine.auth.signup.PasswordInputNavKey
import com.wonjo.deepfine.auth.signup.PasswordInputScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun AuthNavHost(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(LoginEmailNavKey)
    val viewModel = hiltViewModel<AuthViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AuthEffectHandler(
        effect = viewModel.effect,
        backStack = backStack,
    )

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = {
                if (backStack.lastOrNull() == AuthCompleteNavKey) {
                    viewModel.onEvent(AuthContract.Event.CompleteLoginClicked)
                } else if (backStack.size > 1) {
                    backStack.removeLastOrNull()
                }
            },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            transitionSpec = {
                authSlideTransition(forward = true)
            },
            popTransitionSpec = {
                authSlideTransition(forward = false)
            },
            predictivePopTransitionSpec = {
                authSlideTransition(forward = false)
            },
            entryProvider = { route ->
                when (route) {
                    LoginEmailNavKey -> NavEntry(route) {
                        LoginEmailScreen(
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                        )
                    }
                    LoginPasswordNavKey -> NavEntry(route) {
                        LoginPasswordScreen(
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                            onEmailEdited = { backStack.navigateToLoginEmail() },
                        )
                    }
                    LoginSignUpNavKey -> NavEntry(route) {
                        LoginSignUpScreen(
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                            onEmailEdited = { backStack.navigateToLoginEmail() },
                        )
                    }
                    NameInputNavKey -> NavEntry(route) {
                        NameInputScreen(
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                            onBackClick = { backStack.removeLastOrNull() },
                        )
                    }
                    PasswordInputNavKey -> NavEntry(route) {
                        PasswordInputScreen(
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                            onBackClick = { backStack.removeLastOrNull() },
                        )
                    }
                    AuthCompleteNavKey -> NavEntry(route) {
                        AuthCompleteScreen(
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                        )
                    }
                    else -> error(route.toString())
                }
            },
        )
    }
}

private const val AuthTransitionDurationMillis = 300

private fun <T : Any> AnimatedContentTransitionScope<Scene<T>>.authSlideTransition(
    forward: Boolean,
): ContentTransform {
    val direction = if (forward) {
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        AnimatedContentTransitionScope.SlideDirection.Right
    }

    return slideIntoContainer(
        towards = direction,
        animationSpec = tween(AuthTransitionDurationMillis),
    ) togetherWith slideOutOfContainer(
        towards = direction,
        animationSpec = tween(AuthTransitionDurationMillis),
    )
}

@Composable
private fun AuthEffectHandler(
    effect: Flow<Effect>,
    backStack: NavBackStack<NavKey>,
) {
    val context = LocalContext.current
    val resolveMessage = rememberAuthUiMessageResolver()

    LaunchedEffect(effect, context, backStack, resolveMessage) {
        effect.collect { authEffect ->
            when (authEffect) {
                Effect.NavigateToLoginEmail -> {
                    backStack.navigateToLoginEmail()
                }
                Effect.NavigateToLoginPassword -> {
                    backStack.navigateSingleTop(LoginPasswordNavKey)
                }
                Effect.NavigateToLoginSignUp -> {
                    backStack.navigateSingleTop(LoginSignUpNavKey)
                }
                Effect.NavigateToName -> {
                    backStack.navigateSingleTop(NameInputNavKey)
                }
                Effect.NavigateToPassword -> {
                    backStack.navigateSingleTop(PasswordInputNavKey)
                }
                Effect.NavigateToComplete -> {
                    backStack.navigateToComplete()
                }
                is Effect.ShowToast -> {
                    Toast.makeText(
                        context,
                        resolveMessage(authEffect.message),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }
}

private fun NavBackStack<NavKey>.navigateSingleTop(route: NavKey) {
    if (lastOrNull() != route) {
        add(route)
    }
}

private fun NavBackStack<NavKey>.navigateToComplete() {
    while (size > 1) {
        removeLastOrNull()
    }

    navigateSingleTop(AuthCompleteNavKey)
}

private fun NavBackStack<NavKey>.navigateToLoginEmail() {
    while (size > 1) {
        removeLastOrNull()
    }
}
