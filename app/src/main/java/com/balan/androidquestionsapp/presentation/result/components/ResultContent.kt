package com.balan.androidquestionsapp.presentation.result.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewResultContent() {
    ResultContent(textResult = R.string.result, onMainClick = {}, state = ResultState())
}


@Composable
fun ResultContent(
    state: ResultState,
    textResult: Int,
    modifier: Modifier = Modifier,
    onMainClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.Star, contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Text(
            text = stringResource(id = textResult),
            fontSize = LocalDimen.current.textSize32,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(LocalDimen.current.spacerPaddingAll32))
        Text(
            "${state.score}/${state.questionSize}",
            fontSize = LocalDimen.current.textSize64
        )
        Spacer(modifier = Modifier.height(LocalDimen.current.spacerPaddingAll32))
        Button(
            onClick = onMainClick,
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            androidx.compose.material3.Text(
                text = stringResource(id = R.string.menu),
                fontSize = 16.sp
            )
        }
    }
}