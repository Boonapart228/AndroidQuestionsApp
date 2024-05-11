package com.balan.androidquestionsapp.presentation.test_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.balan.androidquestionsapp.domain.models.Answer
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.BottomBar
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.TestCheckContent
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.TestFieldContent
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.TestRadioContent
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.TopBarTestScreen

@Composable
fun TestContent(
    state: TestState,
    onIndexPlusClick: () -> Unit,
    onIndexMinusClick: () -> Unit,
    setAnswer: (String) -> Unit,
    onSelectedRadioAnswerClick: (Answer) -> Unit,
    onSelectedCheckAnswerClick: (Answer) -> Unit,
    onMainClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Scaffold(
            topBar = {
                TopBarTestScreen(count = state.questionNumber, size = state.questions.size, onMainClick = onMainClick)
            },
            bottomBar = {
                BottomBar(
                    onIndexPlusClick = onIndexPlusClick,
                    onIndexMinusClick = onIndexMinusClick
                )
            }) {
            Box(modifier = Modifier.padding(it))
            when (state.questions[state.questionNumber].type) {
                "radioButtons" -> {
                    TestRadioContent(
                        modifier = Modifier,
                        answers = state.questions[state.questionNumber].answers,
                        selectedAnswer = state.selectedRadioAnswer,
                        onSelectedAnswerClick = onSelectedRadioAnswerClick,
                        title = state.questions[state.questionNumber].title
                    )
                }

                "checkBox" -> {
                    TestCheckContent(
                        title = state.questions[state.questionNumber].title,
                        modifier = Modifier,
                        selectedAnswers = state.selectedCheckAnswer,
                        answers = state.questions[state.questionNumber].answers,
                        onSelectedAnswerClick = onSelectedCheckAnswerClick,
                    )
                }

                "textField" -> {
                    TestFieldContent(
                        title = state.questions[state.questionNumber].title,
                        modifier = Modifier, answer = state.writtenAnswer, setAnswer = setAnswer
                    )
                }
            }
        }
    }
}




