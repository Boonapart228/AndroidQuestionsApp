package com.balan.androidquestionsapp.presentation.result.components

sealed class ResultNavigationEvent {
    data object NavigationToMenu : ResultNavigationEvent()
}