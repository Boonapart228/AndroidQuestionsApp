package com.balan.androidquestionsapp.presentation.admin.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.presentation.topbar.TopBar
import com.balan.androidquestionsapp.ui.theme.LocalDimen

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
        onMainClick = {},
        isFieldInvalid = { false },
        onShowPasswordClick = { },

        )
}

@Composable
fun AdminContent(
    state: AdminState,
    setPassword: (String) -> Unit,
    onPanelScoreClick: () -> Unit,
    onMainClick: () -> Unit,
    onShowPasswordClick: () -> Unit,
    isFieldInvalid: (Validation) -> Boolean,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_admin_auth))
    BackHandler {
        onMainClick()
    }
    Scaffold(
        topBar = {
            TopBar(
                onClick = onMainClick,
                text = stringResource(id = R.string.results_panel),
                imageVector = Icons.Filled.ArrowBack
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.paddingAll16),
                modifier = Modifier.padding(LocalDimen.current.paddingAll16)
            ) {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    isPlaying = true,
                    modifier = Modifier.size(LocalDimen.current.lottieAnimationSize)
                )
                TextFieldWithValidation(
                    value = state.password,
                    text = stringResource(R.string.input_password),
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_password_24),
                    onValueChange = setPassword,
                    imeAction = ImeAction.Done,
                    textError = state.passwordValidation,
                    isFieldInvalid = isFieldInvalid,
                    showPassword = state.showPassword,
                    onShowPasswordClick = onShowPasswordClick,
                )
                Button(
                    onClick = {
                        keyboardController?.hide()
                        onPanelScoreClick()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(LocalDimen.current.buttonShape),
                    enabled = state.fieldsIsNotEmpty
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in),
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
    showPassword: Boolean,
    text: String,
    textError: Validation,
    onValueChange: (String) -> Unit,
    onShowPasswordClick: () -> Unit,
    isFieldInvalid: (Validation) -> Boolean,
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
                    modifier = Modifier.size(LocalDimen.current.iconSize24)
                )
            },
            trailingIcon = {
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
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = imeAction
            ),
            visualTransformation = if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
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
