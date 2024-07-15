package com.balan.androidquestionsapp.presentation.sign_in.components

sealed class SignInEvent {
    data object NavigationToMain : SignInEvent()
    data object NavigationToSignUp : SignInEvent()
}