package com.balan.androidquestionsapp.presentation.sign_in.components

sealed class SignInNavigationEvent {
    data object NavigationToSignIn : SignInNavigationEvent()
    data object NavigationToSignUp : SignInNavigationEvent()
}