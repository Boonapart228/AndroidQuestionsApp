package com.balan.androidquestionsapp.presentation.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpContent
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpNavigationEvent
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpViewModel


@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    navigateToSignIn: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is SignUpNavigationEvent.NavigationToSignIn -> navigateToSignIn()
            }
        }
    }
    SignUpContent(
        state = state,
        onLoginChange = viewModel::setName,
        onPasswordChange = viewModel::setPassword,
        onEmailChange = viewModel::setEmail,
        onSignUpClick = viewModel::onSignUpClick,
        onSignInClick = viewModel::onSignInClick,
        isFieldsNotEmpty = viewModel::isFieldsNotEmpty
    )
}