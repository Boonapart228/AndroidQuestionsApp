package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.domain.models.Answer
import com.balan.androidquestionsapp.ui.theme.LocalColors
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun TestRadioContent(
    title: String,
    selectedAnswer: Answer?,
    answers: List<Answer>,
    onAnswerClick: (Answer) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = LocalColors.current.backGround)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = LocalDimen.current.questionPaddingHorizontal)
                .weight(1f)
        ) {
            TestTitleAnswer(title = title)
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight16))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimen.current.scrollContainerHeight)
                    .verticalScroll(rememberScrollState())
            ) {
                answers.forEach { answer ->
                    Row(
                        modifier = Modifier

                            .padding(vertical = LocalDimen.current.questionVerticalPadding)
                            .clickable { onAnswerClick(answer) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RadioButton(
                            colors = RadioButtonDefaults.colors(
                                selectedColor = LocalColors.current.black,
                                unselectedColor = LocalColors.current.black,
                            ),
                            selected = selectedAnswer == answer,
                            onClick = { onAnswerClick(answer) }
                        )
                        Spacer(modifier = Modifier.width(LocalDimen.current.spacerWidth8))
                        Text(
                            text = answer.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(LocalDimen.current.answerClip))
                                .background(color = LocalColors.current.white)
                                .padding(horizontal = LocalDimen.current.horizontalPadding24),
                            textAlign = TextAlign.Start,
                            fontSize = LocalDimen.current.answerTextSize
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewRadio() {
    TestRadioContent(
        modifier = Modifier,
        selectedAnswer = Answer(true, "2"),
        answers = listOf(
            Answer(isTrue = false, title = "1"),
            Answer(isTrue = false, title = "3"),
            Answer(isTrue = false, title = "4")
        ),
        onAnswerClick = {},
        title = "Cкільки буде 2 + 2"

    )
}