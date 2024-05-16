package com.balan.androidquestionsapp.presentation.sign_in.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen
import com.balan.androidquestionsapp.ui.theme.LocalProperty


@Composable
fun SignInContent(
    state: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onShowPasswordClick: () -> Unit,
    isFieldsNotEmpty: () -> Boolean,
    modifier: Modifier = Modifier
) {
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
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop64))
                OutlinedTextField(
                    value = state.email,
                    onValueChange = onEmailChange,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = LocalDimen.current.outlinedTextSize,
                    ),
                    label = {
                        Text(
                            text = stringResource(id = R.string.input_email),
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
                        imeAction = ImeAction.Next
                    ),
                )

                Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))
                OutlinedTextField(
                    value = state.password,
                    onValueChange = onPasswordChange,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = LocalDimen.current.outlinedTextSize
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
                    ),
                    trailingIcon = {
                        if (state.showPassword) {
                                IconButton(onClick = onShowPasswordClick) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24),
                                        contentDescription = null
                                    )
                                }
                        } else {
                            IconButton(
                                onClick = onShowPasswordClick
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_visibility_24),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop32))
                Button(
                    onClick = onSignInClick,
                    modifier = Modifier.width(LocalDimen.current.buttonWidth),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    enabled = isFieldsNotEmpty(),
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in), color = Color.Black,
                        fontSize = LocalDimen.current.buttonTextSize24
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
                Text(text = stringResource(id = state.validation.textResId))
                TextButton(onClick = onSignUpClick) {
                    Text(
                        text = stringResource(id = R.string.sign_up),
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
fun SignInScreenPreview() {
    SignInContent(
        state = SignInState(),
        onEmailChange = {},
        onPasswordChange = {},
        onSignInClick = {},
        onSignUpClick = {},
        isFieldsNotEmpty = { false },
        onShowPasswordClick = {}

    )
}