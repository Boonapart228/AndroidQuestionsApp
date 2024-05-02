package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun TestField(
    title: String,
    answer: String,
    setAnswer: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimen.current.questionPaddingHorizontal)
                .weight(1f)
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(LocalDimen.current.questionTitleShape))
                    .clip(RoundedCornerShape(LocalDimen.current.questionTitleClip))
                    .padding(LocalDimen.current.questionTitlePaddingAll),
                fontSize = LocalDimen.current.questionTitleTextSize,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(LocalDimen.current.spacerPaddingAll16))
            OutlinedTextField(
                value = answer,
                onValueChange = setAnswer,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = LocalDimen.current.outlinedTextSize
                ),
                shape = RoundedCornerShape(LocalDimen.current.outlinedShape),
                modifier = Modifier
                    .clip(RoundedCornerShape(LocalDimen.current.outlinedClip))
                    .width(LocalDimen.current.outlinedTextWidth)
                    .height(LocalDimen.current.outlinedTextHeight)
                    .background(Color.White),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTextField() {
    TestField(title = "", answer = "", setAnswer = { }, modifier = Modifier)
}