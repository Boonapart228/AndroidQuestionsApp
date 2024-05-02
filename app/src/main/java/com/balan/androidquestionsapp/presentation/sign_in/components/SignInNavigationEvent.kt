package com.balan.androidquestionsapp.presentation.sign_in.components

sealed class SignInNavigationEvent {
    data object NavigationSignIn : SignInNavigationEvent()
    data object NavigationSignUp : SignInNavigationEvent()
}