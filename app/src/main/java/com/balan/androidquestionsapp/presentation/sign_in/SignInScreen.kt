package com.balan.androidquestionsapp.presentation.sign_in

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInContent
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInEvent
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    navigateToSignUp: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        launch {
            viewModel.event.collect {
                when (it) {
                    is SignInEvent.NavigationToMain -> {
                        Toast.makeText(
                            context,
                            R.string.successful_authorization,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        navigateToSignIn()
                    }

                    is SignInEvent.NavigationToSignUp -> navigateToSignUp()
                }
            }
        }
    }

    SignInContent(
        state = state,
        onEmailChange = viewModel::setEmail,
        onPasswordChange = viewModel::setPassword,
        onSignInClick = viewModel::onSignInClick,
        onSignUpClick = viewModel::onSignUpClick,
        onShowPasswordClick = viewModel::onShowPasswordClick,
        onClearClick = viewModel::onClearClick

    )
}