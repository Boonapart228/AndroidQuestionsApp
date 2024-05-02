package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.topbar.TopBar
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.ButtonColor
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun MainContent(
    onTestJuniorClick: () -> Unit,
    onTestMiddleClick: () -> Unit,
    onTestSeniorClick: () -> Unit,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                onSignInClick = onSignInClick,
                imageVector = Icons.Filled.ArrowBack
            )
        }
    ) {
        Box(modifier = Modifier.padding(it))
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Background)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxSize()
            ) {

                Spacer(modifier = Modifier.padding(LocalDimen.current.spacerPaddingAll32))
                MainButton(
                    text = R.string.question_junior,
                    onClick = onTestJuniorClick
                )
                Spacer(modifier = Modifier.padding(LocalDimen.current.spacerPaddingAll32))
                MainButton(text = R.string.question_middle, onClick = onTestMiddleClick)
                Spacer(modifier = Modifier.padding(LocalDimen.current.spacerPaddingAll32))
                MainButton(text = R.string.question_senior, onClick = onTestSeniorClick)
            }
        }
    }

}

@Composable
fun MainButton(text: Int, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .padding(LocalDimen.current.buttonCirclePaddingAll)
                .size(LocalDimen.current.buttonCircleSize)
                .background(
                    shape = CircleShape,
                    color = ButtonColor
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor
            )
        ) {}
        Text(
            text = stringResource(id = text),
            fontSize = LocalDimen.current.textSizeQuestion
        )
    }

}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MainContentPreview() {
    MainContent(
        onTestJuniorClick = { },
        onTestMiddleClick = { },
        onTestSeniorClick = { },
        onSignInClick = {},
        modifier = Modifier
    )
}