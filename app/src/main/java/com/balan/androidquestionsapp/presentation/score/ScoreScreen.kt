package com.balan.androidquestionsapp.presentation.score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.balan.androidquestionsapp.presentation.score.components.ScoreContent
import com.balan.androidquestionsapp.presentation.score.components.ScoreNavigationEvent
import com.balan.androidquestionsapp.presentation.score.components.ScoreViewModel

@Composable
fun ScoreScreen(
    viewModel: ScoreViewModel,
    navigateToMain: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is ScoreNavigationEvent.NavigationToMain -> navigateToMain()
            }
        }
    }
    ScoreContent(
        onMainClick = viewModel::onMainClick,
        viewModel = viewModel,
        onSortClick = viewModel::sort,
        onActiveClick = viewModel::onToggleMenuClick,
        onConfirmationClick = viewModel::handleDialogAction,
        onActive = viewModel::onActive
    )
}