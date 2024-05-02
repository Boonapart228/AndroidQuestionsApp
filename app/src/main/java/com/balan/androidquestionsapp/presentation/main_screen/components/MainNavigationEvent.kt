package com.balan.androidquestionsapp.presentation.main_screen.components

sealed class MainNavigationEvent {
    data object NavigationTest : MainNavigationEvent()
    data object NavigationSignIn : MainNavigationEvent()
}