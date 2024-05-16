package com.balan.androidquestionsapp.presentation.score.components

sealed class ScoreNavigationEvent {
    data object NavigationToMain : ScoreNavigationEvent()
}