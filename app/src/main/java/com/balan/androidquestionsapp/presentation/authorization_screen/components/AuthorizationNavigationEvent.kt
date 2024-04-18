package com.balan.androidquestionsapp.presentation.authorization_screen.components

sealed class AuthorizationNavigationEvent {
    data object NavigationSingIn : AuthorizationNavigationEvent()
    data object NavigationSingUp : AuthorizationNavigationEvent()
}