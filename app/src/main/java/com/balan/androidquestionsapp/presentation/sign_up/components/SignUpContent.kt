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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.topbar.TopBar
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignUpScreenPreview() {
    SignUpContent(
        state = SignUpState(),
        onLoginChange = {},
        onPasswordChange = {},
        onEmailChange = {},
        onSignUpClick = {},
        onSignInClick = {},
        isFieldsNotEmpty = { false },
        modifier = Modifier,
    )
}

@Composable
fun SignUpContent(
    state: SignUpState,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    isFieldsNotEmpty: () -> Boolean,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                onClick = onSignInClick,
                imageVector = Icons.Filled.ArrowBack
            )
        }
    ) {
        Box(modifier = Modifier.padding(it))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .background(Background)
                .padding(LocalDimen.current.paddingAll16)
        ) {
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = null,
                modifier = Modifier.size(LocalDimen.current.iconSize130)
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight16))
            Text(
                text = stringResource(id = R.string.registration),
                textAlign = TextAlign.Center,
                fontSize = LocalDimen.current.textSize24,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
            TextFieldBox(
                value = state.email,
                text = stringResource(id = R.string.input_email),
                imageVector = Icons.Filled.Email,
                onValueChange = onEmailChange,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight16))
            TextFieldBox(
                value = state.name,
                text = stringResource(id = R.string.input_login),
                imageVector = Icons.Filled.AccountBox,
                onValueChange = onLoginChange,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight16))
            TextFieldBox(
                value = state.password,
                text = stringResource(id = R.string.input_password),
                imageVector = ImageVector.vectorResource(R.drawable.baseline_password_24),
                onValueChange = onPasswordChange,
                imeAction = ImeAction.Done
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
            Button(
                onClick = onSignUpClick,
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.fillMaxWidth(),
                enabled = isFieldsNotEmpty(),
                shape = RoundedCornerShape(LocalDimen.current.buttonShape)
            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    fontSize = LocalDimen.current.textSize16
                )
            }
            Text(
                text = stringResource(id = state.valid.textResId),
                color = Color.Black,
                fontSize = LocalDimen.current.textSize16,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TextFieldBox(
    value: String,
    imeAction: ImeAction,
    text: String,
    onValueChange: (String) -> Unit,
    imageVector: ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = text,
                fontSize = LocalDimen.current.textSize16
            )
        },
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(LocalDimen.current.iconSize30)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        modifier = Modifier.fillMaxWidth()
    )
}