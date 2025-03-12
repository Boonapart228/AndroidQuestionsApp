package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
fun TestCheckContent(
    title: String,
    selectedAnswers: List<Answer>,
    answers: List<Answer>,
    onAnswerClick: (Answer) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            LocalDimen.current.spacerHeight16,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimen.current.questionPaddingHorizontal)
        ) {
            AnswerTitle(title = title)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = LocalDimen.current.questionPaddingHorizontal)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                answers.forEach { answer ->
                    val selected = answer in selectedAnswers
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
                        Checkbox(
                            checked = selected,
                            onCheckedChange = {
                                onAnswerClick(answer)
                            },
                            colors = CheckboxDefaults.colors()
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
fun PreviewCheck() {
    TestCheckContent(
        title = "qweqwe",
        modifier = Modifier,
        selectedAnswers = emptyList(),
        answers = listOf(
            Answer(isTrue = false, title = "qwe")
        ),
        onAnswerClick = { })
}