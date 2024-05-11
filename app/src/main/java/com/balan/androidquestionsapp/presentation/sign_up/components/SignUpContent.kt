package com.balan.androidquestionsapp.presentation.sign_up.components

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
import androidx.compose.material.icons.filled.ArrowBack
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
import com.balan.androidquestionsapp.ui.theme.LocalProperty

@Composable
fun SignUpContent(
    state: SignUpState,
    setLogin: (String) -> Unit,
    setPassword: (String) -> Unit,
    setEmail: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBar(onClick = onSignInClick,
            imageVector = Icons.Filled.ArrowBack) }
    ) {
        Box(modifier = Modifier.padding(it))
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Background),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.weight(LocalProperty.current.weight1),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop64))

                    SignUpTextField(
                        value = state.email,
                        label = R.string.input_email,
                        onValueChange = setEmail,
                        imeAction = ImeAction.Next
                    )

                    Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))

                    SignUpTextField(
                        value = state.password,
                        label = R.string.input_password,
                        onValueChange = setPassword,
                        imeAction = ImeAction.Next
                    )

                    Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))

                    SignUpTextField(
                        value = state.name,
                        label = R.string.input_login,
                        onValueChange = setLogin,
                        imeAction = ImeAction.Done
                    )

                    Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))
                    Button(
                        onClick = onSignUpClick,
                        modifier = Modifier.width(LocalDimen.current.buttonSignUpWidth),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        enabled = !(state.name.isEmpty() && state.password.isEmpty() && state.email.isEmpty())
                    ) {
                        Text(
                            text = stringResource(id = R.string.sign_up), color = Color.Black,
                            fontSize = LocalDimen.current.buttonTextSize24,
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))

                    Text(text = stringResource(id = state.valid.text))
                }
            }
        }
    }

}


@Composable
fun SignUpTextField(
    value: String,
    label: Int,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = LocalDimen.current.outlinedTextSize
        ),
        label = {
            Text(
                text = stringResource(id = label),
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
            imeAction = imeAction
        )
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignUpScreenPreview() {
    SignUpContent(
        state = SignUpState(),
        setLogin = {},
        setPassword = {},
        setEmail = {},
        onSignUpClick = {},
        onSignInClick = {},
        modifier = Modifier
    )
}