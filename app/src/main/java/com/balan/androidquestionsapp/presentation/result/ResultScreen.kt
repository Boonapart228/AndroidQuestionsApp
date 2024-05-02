package com.balan.androidquestionsapp.presentation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.result.components.ResultContent
import com.balan.androidquestionsapp.presentation.result.components.ResultNavigationEvent
import com.balan.androidquestionsapp.presentation.result.components.ResultViewModel

@Composable
fun ResultScreen(
    navigateMain: () -> Unit,
    viewModel: ResultViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is ResultNavigationEvent.NavigationMenu -> navigateMain()
            }
        }
    }
    val state by viewModel.state.collectAsState()
    ResultContent(
        textResult = R.string.result,
        score = state.score,
        size = state.size,
        onMainClick = viewModel::onMainClick
    )
}