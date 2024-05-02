package com.balan.androidquestionsapp.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.balan.androidquestionsapp.presentation.main_screen.components.MainContent
import com.balan.androidquestionsapp.presentation.main_screen.components.MainNavigationEvent
import com.balan.androidquestionsapp.presentation.main_screen.components.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigationTest: () -> Unit,
    navigationSignIn: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is MainNavigationEvent.NavigationTest -> navigationTest()
                is MainNavigationEvent.NavigationSignIn -> navigationSignIn()
            }
        }
    }
    MainContent(
        onTestJuniorClick = viewModel::onTestJuniorClick,
        onTestMiddleClick = viewModel::onTestMiddleClick,
        onTestSeniorClick = viewModel::onTestSeniorClick,
        onSignInClick = viewModel::onSignInClick,
    )
}