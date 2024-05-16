package com.balan.androidquestionsapp.presentation.result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.result.components.contents.ResultBottomBar
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun ResultContent(
    state: ResultState,
    textResult: Int,
    modifier: Modifier = Modifier,
    onMainClick: () -> Unit
) {
    Scaffold(
        bottomBar = { ResultBottomBar(onMainClick = onMainClick) }
    ) {
        Box(modifier = Modifier.padding(it))
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = textResult),
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(LocalDimen.current.textResultShape)
                    )
                    .padding(LocalDimen.current.textPaddingAll16),
                fontSize = LocalDimen.current.textSize32,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(LocalDimen.current.spacerPaddingAll32))
            Text(
                "${state.score}/${state.questionSize}",
                fontSize = LocalDimen.current.textSize64
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewResultContent() {
    ResultContent(textResult = R.string.result, onMainClick = {}, state = ResultState())
}