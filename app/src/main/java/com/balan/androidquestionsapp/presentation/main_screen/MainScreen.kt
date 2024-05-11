package com.balan.androidquestionsapp.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.balan.androidquestionsapp.presentation.main_screen.components.MainContent
import com.balan.androidquestionsapp.presentation.main_screen.components.MainNavigationEvent
import com.balan.androidquestionsapp.presentation.main_screen.components.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigateToTest: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToAdmin: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is MainNavigationEvent.NavigationToTest -> navigateToTest()
                is MainNavigationEvent.NavigationToSignIn -> navigateToSignIn()
                is MainNavigationEvent.NavigationToAdmin -> navigateToAdmin()
            }
        }
    }
    MainContent(
        onTestJuniorClick = viewModel::onTestJuniorClick,
        onTestMiddleClick = viewModel::onTestMiddleClick,
        onTestSeniorClick = viewModel::onTestSeniorClick,
        onSignInClick = viewModel::onSignInClick,
        onAdminJuniorDoubleClick = viewModel::onAdminJuniorDoubleClick,
        onAdminMiddleDoubleClick = viewModel::onAdminMiddleDoubleClick,
        onAdminSeniorDoubleClick = viewModel::onAdminDoubleSeniorClick,
        )
}