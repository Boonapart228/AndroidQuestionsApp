package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun AnswerTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = LocalDimen.current.titleBorderWidth,
                color = Color.Black,
                shape = RoundedCornerShape(LocalDimen.current.titleBorderShape),
            )
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(LocalDimen.current.titleBorderShape)
            )
            .padding(LocalDimen.current.questionTitlePaddingAll),
        fontSize = LocalDimen.current.questionTitleTextSize,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview
@Composable
fun AnswerTitlePreview() {
    AnswerTitle(title = "qwe")
}