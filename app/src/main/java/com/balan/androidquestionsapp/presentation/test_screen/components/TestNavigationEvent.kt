package com.balan.androidquestionsapp.presentation.test_screen.components

sealed class TestNavigationEvent {
    data object NavigationMenu : TestNavigationEvent()
    data object NavigationResult : TestNavigationEvent()

}

