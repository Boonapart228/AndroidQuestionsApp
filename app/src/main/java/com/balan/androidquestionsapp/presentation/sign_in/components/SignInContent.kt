package com.balan.androidquestionsapp.presentation.sign_in.components


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
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
import com.balan.androidquestionsapp.domain.models.DialogAction
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.presentation.sign_in.contents.ExitAppConfirmationDialog
import com.balan.androidquestionsapp.presentation.sign_in.contents.TopBarSignIn
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun SignInContent(
    state: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onExitAppClick: () -> Unit,
    onAutoLoginClick: () -> Unit,
    isFieldInvalid: (Validation) -> Boolean,
    onConfirmationClick: (DialogAction) -> Unit,
    onShowPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_auth))
    BackHandler { return@BackHandler }
    Scaffold(
        topBar = { TopBarSignIn(onClick = onExitAppClick) }
    ) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(it),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = LocalDimen.current.spacerHeight16),
                modifier = modifier
                    .fillMaxSize()
                    .padding(LocalDimen.current.paddingAll16)
            ) {
                Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
                LottieAnimation(
                    composition = composition,
                    isPlaying = true,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(LocalDimen.current.lottieAnimationSize)
                )
                TextFieldWithValidation(
                    value = state.email,
                    text = stringResource(id = R.string.input_email),
                    onValueChange = onEmailChange,
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.baseline_email_24),
                    trailingIcon = Icons.Rounded.Close,
                    imeAction = ImeAction.Next,
                    isFieldInvalid = isFieldInvalid,
                    textError = state.validationEmail,

                    )
                TextFieldWithValidation(
                    value = state.password,
                    text = stringResource(id = R.string.input_password),
                    onValueChange = onPasswordChange,
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.baseline_password_24),
                    trailingIcon = ImageVector.vectorResource(R.drawable.baseline_visibility_off_24),
                    imeAction = ImeAction.Done,
                    showPassword = state.showPassword,
                    isPasswordField = true,
                    isFieldInvalid = isFieldInvalid,
                    textError = state.validationPassword,
                    onShowPasswordClick = onShowPasswordClick
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.selectedAutoLogin,
                        onCheckedChange = { onAutoLoginClick() },
                        modifier = Modifier
                            .size(LocalDimen.current.checkBoxSize)
                            .align(Alignment.CenterVertically)
                            .padding(start = LocalDimen.current.checkBoxStartPadding)
                    )
                    Text(
                        text = stringResource(id = R.string.remember_me),
                        modifier = Modifier.padding(start = LocalDimen.current.textStartPadding)
                    )
                }
                Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
                Button(
                    onClick = {
                        keyboardController?.hide()
                        onSignInClick()
                    },
                    enabled = state.fieldsIsNotEmpty,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(LocalDimen.current.buttonShape)
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in),
                        fontSize = LocalDimen.current.textSize16
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LocalDimen.current.horizontalRowSpace),
                    modifier = Modifier
                        .fillMaxWidth()

                ) {

                    Divider(
                        thickness = LocalDimen.current.driverThickness,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = stringResource(id = R.string.either),
                        fontSize = LocalDimen.current.textSize16,
                        modifier = Modifier.padding(horizontal = LocalDimen.current.horizontalPadding8)
                    )
                    Divider(
                        thickness = LocalDimen.current.driverThickness,
                        modifier = Modifier.weight(1f)
                    )
                }
                OutlinedButton(
                    onClick = {
                        keyboardController?.hide()
                        onSignUpClick()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up),
                        fontSize = LocalDimen.current.textSize16,
                        fontWeight = FontWeight.Bold,
                    )

                }
                if (state.isExitDialogVisible) {
                    ExitAppConfirmationDialog(
                        onDismissRequest = { onConfirmationClick(DialogAction.DISMISS) },
                        onConfirmation = { onConfirmationClick(DialogAction.CONFIRM) },
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
        onShowPasswordClick = {},
        isFieldInvalid = { false },
        onConfirmationClick = {},
        onExitAppClick = {},
        onAutoLoginClick = {})
}

@Composable
fun TextFieldWithValidation(
    value: String,
    text: String = "",
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    textError: Validation,
    isFieldInvalid: (Validation) -> Boolean,
    imeAction: ImeAction = ImeAction.Default,
    isPasswordField: Boolean = false,
    showPassword: Boolean = false,
    onShowPasswordClick: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = LocalDimen.current.textSize16,
            ),
            label = {
                Text(
                    text = text,
                    fontSize = LocalDimen.current.textSize16
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPasswordField) KeyboardType.Password else KeyboardType.Text,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(LocalDimen.current.iconSize24)
                )
            },
            trailingIcon = {
                if (isPasswordField) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = LocalDimen.current.iconDeleteEndPadding)
                    ) {
                        IconButton(onClick = onShowPasswordClick) {
                            Icon(
                                imageVector = if (!showPassword)
                                    ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24)
                                else
                                    ImageVector.vectorResource(id = R.drawable.baseline_visibility_24),
                                contentDescription = null,
                                modifier = Modifier.size(LocalDimen.current.iconSize24)
                            )
                        }
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .size(LocalDimen.current.iconSize24)
                                .clickable { onValueChange("") }
                        )
                    }
                } else {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(LocalDimen.current.iconSize24)
                            .clickable { onValueChange("") }
                    )
                }
            },
            visualTransformation = if (isPasswordField && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            isError = isFieldInvalid(textError),
            modifier = Modifier.fillMaxWidth(),
        )
        if (isFieldInvalid(textError)) {
            Text(
                text = stringResource(id = textError.textResId),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}