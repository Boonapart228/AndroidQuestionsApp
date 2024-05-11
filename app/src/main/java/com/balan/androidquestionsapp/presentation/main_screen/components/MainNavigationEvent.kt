package com.balan.androidquestionsapp.presentation.main_screen.components

sealed class MainNavigationEvent {
    data object NavigationToTest : MainNavigationEvent()
    data object NavigationToSignIn : MainNavigationEvent()
    data object NavigationToAdmin : MainNavigationEvent()
}