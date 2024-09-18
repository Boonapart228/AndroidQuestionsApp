package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.domain.models.Answer
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
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                LocalDimen.current.spacerHeight16,
                Alignment.Top
            ),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = LocalDimen.current.questionPaddingHorizontal)
                .weight(1f)
        ) {
            AnswerTitle(title = title)
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
                            .clickable(onClick = { onAnswerClick(answer) })
                            .border(
                                width = LocalDimen.current.borderAnswerWidth,
                                color = Color.Black,
                                shape = RoundedCornerShape(LocalDimen.current.shapeAnswer)
                            )
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            LocalDimen.current.spacerWidth8,
                            Alignment.Start
                        )
                    ) {
                        RadioButton(
                            colors = RadioButtonDefaults.colors(),
                            selected = selectedAnswer == answer,
                            onClick = { onAnswerClick(answer) }
                        )
                        Text(
                            text = answer.title,
                            modifier = Modifier
                                .widthIn(max = LocalDimen.current.textWidthMax)
                                .padding(vertical = LocalDimen.current.textQuestionVerticalPadding),
                            textAlign = TextAlign.Start,
                            fontSize = LocalDimen.current.answerTextSize,
                            overflow = TextOverflow.Visible
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