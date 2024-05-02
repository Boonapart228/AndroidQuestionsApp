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
    navigateMain: () -> Unit,
    navigateResult : () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is TestNavigationEvent.NavigationMenu -> navigateMain()
                is TestNavigationEvent.NavigationResult -> navigateResult()
            }
        }
    }
    val state by viewModel.state.collectAsState()
    TestContent(
        state = state,
        onIndexPlusClick = viewModel::indexPlus,
        onIndexMinusClick = viewModel::indexMinus,
        onSelectedRadioAnswerClick = viewModel::onAnswerRadioButtonClick,
        onSelectedCheckAnswerClick = viewModel::onAnswerCheckButtonClick,
        onMainClick = viewModel::onMainClick,
        setAnswer = viewModel::setAnswer,
    )
}