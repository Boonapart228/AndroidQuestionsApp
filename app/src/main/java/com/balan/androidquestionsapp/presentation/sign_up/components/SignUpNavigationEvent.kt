package com.balan.androidquestionsapp.presentation.sign_up.components

sealed class SignUpNavigationEvent {
    data object NavigationToSignIn : SignUpNavigationEvent()
}