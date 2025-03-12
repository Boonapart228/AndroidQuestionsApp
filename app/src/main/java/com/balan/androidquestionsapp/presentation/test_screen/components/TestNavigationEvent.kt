package com.balan.androidquestionsapp.presentation.test_screen.components

sealed class TestNavigationEvent {
    data object NavigationToMenu : TestNavigationEvent()
    data object NavigationToResult : TestNavigationEvent()
}

