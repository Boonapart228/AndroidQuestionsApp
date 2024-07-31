package com.balan.androidquestionsapp.presentation.sign_up.components

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
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.InputFieldType
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.presentation.topbar.TopBar
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
        onSecondPasswordChange = {},
        onEmailChange = {},
        onSignUpClick = {},
        onSignInClick = {},
        isFieldInvalid = { false },
        onClearClick = {},
        modifier = Modifier,
    )
}

@Composable
fun SignUpContent(
    state: SignUpState,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSecondPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    onClearClick: (InputFieldType) -> Unit,
    isFieldInvalid: (Validation) -> Boolean,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_registration))
    Scaffold(
        topBar = {
            TopBar(
                onClick = onSignInClick,
                text = stringResource(id = R.string.registration),
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
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = LocalDimen.current.spacerHeight16),
                modifier = modifier
                    .fillMaxSize()
                    .padding(LocalDimen.current.paddingAll16)
            ) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(LocalDimen.current.lottieAnimationSize),
                    isPlaying = true,
                    iterations = LottieConstants.IterateForever
                )
                TextFieldWithValidation(
                    value = state.email,
                    text = stringResource(id = R.string.input_email),
                    imageVector = Icons.Filled.Email,
                    onValueChange = onEmailChange,
                    imeAction = ImeAction.Next,
                    textVisible = true,
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
                    textVisible = true,
                    textError = state.loginValidation,
                    isFieldInvalid = isFieldInvalid,
                    onClearClick = { onClearClick(InputFieldType.LOGIN) }
                )
                TextFieldWithValidation(
                    value = state.password,
                    text = stringResource(id = R.string.input_password),
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_password_24),
                    onValueChange = onPasswordChange,
                    imeAction = ImeAction.Next,
                    textVisible = false,
                    textError = state.passwordValidation,
                    isFieldInvalid = isFieldInvalid,
                    onClearClick = { onClearClick(InputFieldType.PASSWORD) }
                )
                TextFieldWithValidation(
                    value = state.secondaryPassword,
                    text = stringResource(id = R.string.repeat_password),
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_password_24),
                    onValueChange = onSecondPasswordChange,
                    imeAction = ImeAction.Done,
                    textVisible = false,
                    textError = if (state.passwordValidation == Validation.PASSWORD_DO_NOT_MATCH) state.passwordValidation else Validation.VALID,
                    isFieldInvalid = isFieldInvalid,
                    onClearClick = { onClearClick(InputFieldType.PASSWORD) }
                )
                Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
                Button(
                    onClick = {
                        keyboardController?.hide()
                        onSignUpClick()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.fieldsIsNotEmpty,
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
    textVisible: Boolean,
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
            visualTransformation = if (textVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                keyboardType = if (textVisible) KeyboardType.Text else KeyboardType.Password,
                imeAction = imeAction
            ),
            modifier = Modifier.fillMaxWidth(),
            isError = isFieldInvalid(textError)
        )
        if (isFieldInvalid(textError)) {
            Text(
                text = stringResource(id = textError.textResId),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}