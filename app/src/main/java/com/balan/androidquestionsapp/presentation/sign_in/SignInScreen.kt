package com.balan.androidquestionsapp.presentation.sign_in

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInContent
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInNavigationEvent
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInToastEvent
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
                    is SignInNavigationEvent.NavigationToSignIn -> navigateToSignIn()
                    is SignInNavigationEvent.NavigationToSignUp -> navigateToSignUp()
                }
            }
        }
        launch {
            viewModel.toastEvent.collect {
                when (it) {
                    is SignInToastEvent.SuccessAuthorization -> {
                        Toast.makeText(
                            context,
                            R.string.successful_authorization,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
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