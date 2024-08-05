package com.balan.androidquestionsapp.presentation.sign_up

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpContent
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpEvent
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpViewModel


@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    navigateToSignIn: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context: Context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is SignUpEvent.NavigationSuccessRegistrationToSignIn -> {
                    Toast.makeText(
                        context,
                        R.string.successful_registration,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    navigateToSignIn()
                }

                is SignUpEvent.NavigationToSignIn -> {
                    navigateToSignIn()
                }
            }
        }
    }

    SignUpContent(
        state = state,
        onLoginChange = viewModel::setName,
        onPasswordChange = viewModel::setPassword,
        onConfirmPasswordChange = viewModel::setConfirmPassword,
        onEmailChange = viewModel::setEmail,
        onSignUpClick = viewModel::onSignUpClick,
        onSignInClick = viewModel::onSignInClick,
        isFieldInvalid = viewModel::isErrorValidation,
        onShowPasswordClick = viewModel::onShowPasswordClick,
    )
}