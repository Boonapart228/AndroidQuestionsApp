package com.balan.androidquestionsapp.presentation.authorization_screen.components

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.balan.androidquestionsapp.ui.theme.BackGround
import com.balan.androidquestionsapp.ui.theme.LocalDimen
import com.balan.androidquestionsapp.ui.theme.LocalProperty


@Composable
fun AuthorizationContent(
    modifier: Modifier = Modifier,
    login: String,
    password: String,
    setLogin: (String) -> Unit,
    setPassword: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSingUpClick: () -> Unit,
    errorMessage: Int
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackGround),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.weight(LocalProperty.current.weight1),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop64))
                OutlinedTextField(
                    value = login,
                    onValueChange = setLogin,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = LocalDimen.current.outlinedTextSize,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.input_login),
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = LocalDimen.current.textSize,
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
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))
                OutlinedTextField(
                    value = password,
                    onValueChange = setPassword,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = LocalDimen.current.outlinedTextSize
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.input_password),
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = LocalDimen.current.textSize,
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
                    onClick = onSignInClick,
                    modifier = Modifier.width(LocalDimen.current.buttonWidth),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = stringResource(id = R.string.authorization), color = Color.Black,
                        fontSize = LocalDimen.current.buttonTextSize
                    )
                }
                Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))
            }
        }
        Box(modifier = Modifier.weight(LocalProperty.current.weight05)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = errorMessage))
                TextButton(onClick = onSingUpClick) {
                    Text(
                        text = stringResource(id = R.string.registration),
                        fontSize = LocalDimen.current.textButtonTextSize,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AuthorizationScreenPreview() {
    AuthorizationContent(
        login = "",
        password = "",
        setLogin = {},
        setPassword = {},
        onSignInClick = {},
        errorMessage = R.string.clear,
        onSingUpClick = {},
    )
}