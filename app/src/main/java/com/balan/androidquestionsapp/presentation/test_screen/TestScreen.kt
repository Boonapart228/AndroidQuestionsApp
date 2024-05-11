package com.balan.androidquestionsapp.presentation.test_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.balan.androidquestionsapp.presentation.test_screen.components.TestContent
import com.balan.androidquestionsapp.presentation.test_screen.components.TestNavigationEvent
import com.balan.androidquestionsapp.presentation.test_screen.components.TestViewModel

@Composable
fun TestScreen(
    viewModel: TestViewModel,
    navigateToMain: () -> Unit,
    navigateToResult : () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is TestNavigationEvent.NavigationToMenu -> navigateToMain()
                is TestNavigationEvent.NavigationToResult -> navigateToResult()
            }
        }
    }
    val state by viewModel.state.collectAsState()
    TestContent(
        state = state,
        onIndexPlusClick = viewModel::nextQuestion,
        onIndexMinusClick = viewModel::previousQuestion,
        onSelectedRadioAnswerClick = viewModel::onAnswerRadioButtonClick,
        onSelectedCheckAnswerClick = viewModel::onAnswerCheckButtonClick,
        onMainClick = viewModel::onMainClick,
        setAnswer = viewModel::setAnswer,
    )
}