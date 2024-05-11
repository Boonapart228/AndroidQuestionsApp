package com.balan.androidquestionsapp.presentation.admin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.topbar.TopBar
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun AdminContent(
    state: AdminState,
    setPassword: (String) -> Unit,
    onPanelScoreClick: () -> Unit,
    onMainClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBar(onClick = onMainClick, imageVector = Icons.Filled.Home) }
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
                text = stringResource(id = R.string.results_panel),
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(LocalDimen.current.questionTitleShape)
                    )
                    .clip(RoundedCornerShape(LocalDimen.current.questionTitleClip))
                    .padding(LocalDimen.current.spacerPaddingAll8),
                fontSize = LocalDimen.current.textSize32,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))
            OutlinedTextField(
                value = state.password,
                onValueChange = setPassword,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = LocalDimen.current.outlinedTextSize,
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.input_password),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = LocalDimen.current.textSize24,
                        textAlign = TextAlign.Center
                    )
                },
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
            Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))
            Button(
                onClick = onPanelScoreClick,
                modifier = Modifier.width(LocalDimen.current.buttonWidth),
                colors = ButtonDefaults.buttonColors(Color.White),
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    color = Color.Black,
                    fontSize = LocalDimen.current.buttonTextSize24
                )
            }
            Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))
            Text(text = stringResource(id = state.inValidPassword.text))
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun AdminContentPreview() {
    AdminContent(
        state = AdminState(),
        onPanelScoreClick = {},
        setPassword = {},
        modifier = Modifier,
        onMainClick = {}
    )
}