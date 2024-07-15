package com.balan.androidquestionsapp.presentation.sign_in.components

sealed class SignInEvent {
    data object NavigationToSignIn : SignInEvent()
    data object NavigationToSignUp : SignInEvent()
}