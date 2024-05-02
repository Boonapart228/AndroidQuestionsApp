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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.domain.models.Answer
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun TestRadio(
    title: String,
    selectedAnswer: Answer?,
    answers: List<Answer>,
    modifier: Modifier = Modifier,
    onSelectedAnswerClick: (Answer) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.run {
            fillMaxSize()
                .background(color = Background)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimen.current.questionPaddingHorizontal)
                .weight(1f)
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(LocalDimen.current.questionTitleShape)
                    )
                    .clip(RoundedCornerShape(LocalDimen.current.questionTitleClip))
                    .padding(LocalDimen.current.questionTitlePaddingAll),
                fontSize = LocalDimen.current.questionTitleTextSize,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight)) // Add space between text and answers
            answers.forEach { answer ->
                Row(
                    modifier = Modifier
                        .padding(vertical = LocalDimen.current.questionVerticalPadding)
                        .clickable { onSelectedAnswerClick(answer) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RadioButton(
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Black,
                            unselectedColor = Color.Black,
                        ),
                        selected = selectedAnswer == answer,
                        onClick = { onSelectedAnswerClick(answer) }
                    )
                    Spacer(modifier = Modifier.width(LocalDimen.current.spacerWidth))
                    Text(
                        text = answer.title,
                        modifier = Modifier.fillMaxWidth()
                            .clip(shape = RoundedCornerShape(LocalDimen.current.answerClip))
                            .background(color = Color.White),
                        textAlign = TextAlign.Center,
                        fontSize = LocalDimen.current.answerTextSize
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewRadio() {
    TestRadio(
        modifier = Modifier,
        selectedAnswer = Answer(true, "2"),
        answers = listOf(
            Answer(isTrue = false, title = "1"),
            Answer(isTrue = false, title = "3"),
            Answer(isTrue = false, title = "4")
        ),
        onSelectedAnswerClick = {},
        title = "Cкільки буде 2 + 2"

    )
}