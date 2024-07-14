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
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpNavigationEvent
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpToastEvent
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpViewModel
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    navigateToSignIn: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context: Context = LocalContext.current
    LaunchedEffect(Unit) {
        launch {
            viewModel.toastEvent.collect {
                when (it) {
                    is SignUpToastEvent.SuccessRegistration -> {
                        Toast.makeText(
                            context,
                            R.string.successful_registration,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
        launch {
            viewModel.event.collect {
                when (it) {
                    is SignUpNavigationEvent.NavigationToSignIn -> navigateToSignIn()
                }
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
        isFieldInvalid = viewModel::isErrorValidation,
        onClearClick = viewModel::onClearClick,
    )
}