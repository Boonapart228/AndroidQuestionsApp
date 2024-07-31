package com.balan.androidquestionsapp.presentation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.balan.androidquestionsapp.presentation.result.components.ResultContent
import com.balan.androidquestionsapp.presentation.result.components.ResultNavigationEvent
import com.balan.androidquestionsapp.presentation.result.components.ResultViewModel

@Composable
fun ResultScreen(
    navigateToMain: () -> Unit,
    viewModel: ResultViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is ResultNavigationEvent.NavigationToMenu -> navigateToMain()
            }
        }
    }
    val state by viewModel.state.collectAsState()
    ResultContent(
        state = state,
        getAnimation = viewModel::getAnimation,
        onMainClick = viewModel::onMainClick
    )
}