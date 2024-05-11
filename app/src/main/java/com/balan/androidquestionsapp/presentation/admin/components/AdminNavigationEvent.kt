package com.balan.androidquestionsapp.presentation.admin.components

sealed class AdminNavigationEvent {
    data object NavigationToScore : AdminNavigationEvent()
    data object NavigationToMenu : AdminNavigationEvent()
}