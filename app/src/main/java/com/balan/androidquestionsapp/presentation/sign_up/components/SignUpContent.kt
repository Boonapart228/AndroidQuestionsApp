package com.balan.androidquestionsapp.presentation.sign_up.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.InputFieldType
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.presentation.topbar.TopBar
import com.balan.androidquestionsapp.ui.theme.LocalColors
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
        isFieldInvalid = { false },
        onClearClick = {},
        modifier = Modifier,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpContent(
    state: SignUpState,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    onClearClick: (InputFieldType) -> Unit,
    isFieldInvalid: (Validation) -> Boolean,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopBar(
                onClick = onSignInClick,
                imageVector = Icons.Filled.ArrowBack
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = LocalDimen.current.spacerHeight16),
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(LocalColors.current.background)
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = LocalDimen.current.spacerHeight16),
                modifier = modifier
                    .fillMaxSize()
                    .background(LocalColors.current.background)
                    .padding(LocalDimen.current.paddingAll16)
            ) {


                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = null,
                    modifier = Modifier.size(LocalDimen.current.iconSize130)
                )
                Text(
                    text = stringResource(id = R.string.registration),
                    textAlign = TextAlign.Center,
                    fontSize = LocalDimen.current.textSize24,
                    fontWeight = FontWeight.Bold,
                    color = LocalColors.current.uiElementBlack
                )
                TextFieldWithValidation(
                    value = state.email,
                    text = stringResource(id = R.string.input_email),
                    imageVector = Icons.Filled.Email,
                    onValueChange = onEmailChange,
                    imeAction = ImeAction.Next,
                    textError = state.emailValidation,
                    isFieldInvalid = isFieldInvalid,
                    onClearClick = { onClearClick(InputFieldType.EMAIL) }
                )
                TextFieldWithValidation(
                    value = state.name,
                    text = stringResource(id = R.string.input_login),
                    imageVector = Icons.Filled.AccountBox,
                    onValueChange = onLoginChange,
                    imeAction = ImeAction.Next,
                    textError = state.loginValidation,
                    isFieldInvalid = isFieldInvalid,
                    onClearClick = { onClearClick(InputFieldType.LOGIN) }
                )
                TextFieldWithValidation(
                    value = state.password,
                    text = stringResource(id = R.string.input_password),
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_password_24),
                    onValueChange = onPasswordChange,
                    imeAction = ImeAction.Done,
                    textError = state.passwordValidation,
                    isFieldInvalid = isFieldInvalid,
                    onClearClick = { onClearClick(InputFieldType.PASSWORD) }
                )
                Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
                Button(
                    onClick = {
                        keyboardController?.hide()
                        onSignUpClick()
                    },
                    colors = ButtonDefaults.buttonColors(LocalColors.current.uiElementBlack),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.isFieldsNotEmpty,
                    shape = RoundedCornerShape(LocalDimen.current.buttonShape)
                ) {
                    Text(
                        text = stringResource(id = R.string.register),
                        fontSize = LocalDimen.current.textSize16
                    )
                }
            }
        }
    }
}

@Composable
fun TextFieldWithValidation(
    value: String,
    imeAction: ImeAction,
    text: String,
    textError: Validation,
    onValueChange: (String) -> Unit,
    isFieldInvalid: (Validation) -> Boolean,
    onClearClick: () -> Unit,
    imageVector: ImageVector,
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = LocalDimen.current.textSize16,
            ),
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
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .size(LocalDimen.current.iconSize30)
                        .clickable(onClick = onClearClick)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = imeAction
            ),
            modifier = Modifier.fillMaxWidth(),
            isError = isFieldInvalid(textError)
        )
        if (isFieldInvalid(textError)) {
            Text(
                text = stringResource(id = textError.textResId),
                color = LocalColors.current.warningRed
            )
        }
    }
}