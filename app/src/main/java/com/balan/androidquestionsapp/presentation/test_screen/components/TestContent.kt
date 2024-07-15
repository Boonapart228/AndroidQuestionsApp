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
import com.balan.androidquestionsapp.domain.models.QuestionType
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.BottomBar
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.TestCheckContent
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.TestFieldContent
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.TestRadioContent
import com.balan.androidquestionsapp.presentation.test_screen.components.contents.TopBarTest

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
                TopBarTest(
                    count = state.questionNumber,
                    size = state.questions.size,
                    title = state.title,
                    onMainClick = onMainClick
                )
            },
            bottomBar = {
                BottomBar(
                    onIndexPlusClick = onIndexPlusClick,
                    onIndexMinusClick = onIndexMinusClick
                )
            }) {
            Box(modifier = Modifier.padding(it))
            when (state.questions[state.questionNumber].type) {
                QuestionType.RADIO_BUTTON.type -> {
                    TestRadioContent(
                        answers = state.questions[state.questionNumber].answers,
                        selectedAnswer = state.selectedRadioAnswer,
                        onAnswerClick = onSelectedRadioAnswerClick,
                        title = state.questions[state.questionNumber].title
                    )
                }

                QuestionType.CHECK_BOX.type -> {
                    TestCheckContent(
                        title = state.questions[state.questionNumber].title,
                        selectedAnswers = state.selectedCheckAnswer,
                        answers = state.questions[state.questionNumber].answers,
                        onAnswerClick = onSelectedCheckAnswerClick,
                    )
                }

                QuestionType.TEXT_FIELD.type -> {
                    TestFieldContent(
                        title = state.questions[state.questionNumber].title,
                        answer = state.writtenAnswer, setAnswer = setAnswer
                    )
                }
            }
        }
    }
}




