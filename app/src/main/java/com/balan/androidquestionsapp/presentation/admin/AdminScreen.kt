package com.balan.androidquestionsapp.presentation.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.balan.androidquestionsapp.presentation.admin.components.AdminContent
import com.balan.androidquestionsapp.presentation.admin.components.AdminNavigationEvent
import com.balan.androidquestionsapp.presentation.admin.components.AdminViewModel

@Composable
fun AdminScreen(
    viewModel: AdminViewModel,
    navigateToMain: () -> Unit,
    navigateToScore: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is AdminNavigationEvent.NavigationToMenu -> navigateToMain()
                is AdminNavigationEvent.NavigationToScore -> navigateToScore()
            }
        }
    }
    AdminContent(
        state = state,
        onPanelScoreClick = viewModel::onScoreClick,
        setPassword = viewModel::setPassword,
        onMainClick = viewModel::onMainClick,
        isFieldInvalid = viewModel::isErrorValidation,
        onClearClick = viewModel::onClearClick,

    )
}