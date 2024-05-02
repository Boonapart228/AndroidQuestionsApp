package com.balan.androidquestionsapp.presentation.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInContent
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInNavigationEvent
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInViewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    navigateSignUp: () -> Unit,
    navigateSignIn: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is SignInNavigationEvent.NavigationSignIn -> navigateSignIn()
                is SignInNavigationEvent.NavigationSignUp -> navigateSignUp()
            }
        }
    }
    SignInContent(
        state = state,
        setLogin = viewModel::setLogin,
        setPassword = viewModel::setPassword,
        onSignInClick = viewModel::onSignInClick,
        onSignUpClick = viewModel::onSignUpClick,
    )
}