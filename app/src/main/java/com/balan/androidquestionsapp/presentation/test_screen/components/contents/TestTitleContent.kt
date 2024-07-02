package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun TestTitleAnswer(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(LocalDimen.current.titleBackgroundShape)
            )
            .border(
                width = LocalDimen.current.titleBorderWidth,
                color = Color.Black,
                shape = RoundedCornerShape(LocalDimen.current.titleBorderShape)
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(LocalDimen.current.questionTitleClip))
            .padding(LocalDimen.current.questionTitlePaddingAll),
        fontSize = LocalDimen.current.questionTitleTextSize,
        textAlign = TextAlign.Center
    )
}