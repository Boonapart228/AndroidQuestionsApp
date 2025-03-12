package com.balan.androidquestionsapp.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    val state by viewModel.state.collectAsState()
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
        state = state,
        onLogOutClick = viewModel::onLogOutClick,
        onTestClick = viewModel::onTestClick,
        onTestDoubleClick = viewModel::onTestDoubleClick
    )
}