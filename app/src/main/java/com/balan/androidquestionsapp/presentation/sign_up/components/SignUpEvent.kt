package com.balan.androidquestionsapp.presentation.sign_up.components

sealed class SignUpEvent {
    data object NavigationSuccessRegistrationToSignIn : SignUpEvent()
    data object NavigationToSignIn : SignUpEvent()
}