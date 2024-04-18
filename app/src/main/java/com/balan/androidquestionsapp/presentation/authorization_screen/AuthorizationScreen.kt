package com.balan.androidquestionsapp.presentation.authorization_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.balan.androidquestionsapp.presentation.authorization_screen.components.AuthorizationContent
import com.balan.androidquestionsapp.presentation.authorization_screen.components.AuthorizationNavigationEvent
import com.balan.androidquestionsapp.presentation.authorization_screen.components.AuthorizationViewModel

@Composable
fun AuthorizationScreen(
    viewModel: AuthorizationViewModel,
    navigateSingUp: () -> Unit,
    navigateSingIn: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is AuthorizationNavigationEvent.NavigationSingIn -> navigateSingIn()
                is AuthorizationNavigationEvent.NavigationSingUp -> navigateSingUp()
            }
        }
    }
    AuthorizationContent(
        login = state.login,
        password = state.password,
        setLogin = viewModel::setLogin,
        setPassword = viewModel::setPassword,
        onSignInClick = viewModel::onSignInClick,
        onSingUpClick = viewModel::onSignUpClick,
        errorMessage = state.access,
    )
}