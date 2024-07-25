package com.balan.androidquestionsapp.presentation.sign_in.components


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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.InputFieldType
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun SignInContent(
    state: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onClearClick: (InputFieldType) -> Unit,
    onShowPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(LocalDimen.current.paddingAll16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = LocalDimen.current.spacerHeight16),
    ) {
        Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(LocalDimen.current.iconSize130)
        )
        Text(
            text = stringResource(id = R.string.authorization),
            textAlign = TextAlign.Center,
            fontSize = LocalDimen.current.textSize24,
        )
        TextFieldWithValidation(
            value = state.email,
            text = stringResource(id = R.string.input_email),
            onValueChange = onEmailChange,
            leadingIcon = ImageVector.vectorResource(id = R.drawable.baseline_email_24),
            trailingIcon = Icons.Rounded.Close,
            imeAction = ImeAction.Next,
            onTrailingIconClick = { onClearClick(InputFieldType.EMAIL) }
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
            onShowPasswordClick = onShowPasswordClick
        )
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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
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
        OutlinedButton(onClick = {
            keyboardController?.hide()
            onSignUpClick()
        },
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.sign_up),
                fontSize = LocalDimen.current.textSize16,
                fontWeight = FontWeight.Bold,
            )

        }
        Text(
            text = stringResource(id = state.validation.textResId),
            fontSize = LocalDimen.current.textSize16,
            fontWeight = FontWeight.Bold
        )
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
        onClearClick = {}

    )
}

@Composable
fun TextFieldWithValidation(
    value: String,
    text: String = "",
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    imeAction: ImeAction = ImeAction.Default,
    onTrailingIconClick: () -> Unit = {},
    isPasswordField: Boolean = false,
    showPassword: Boolean = false,
    onShowPasswordClick: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
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
                modifier = Modifier.size(LocalDimen.current.iconSize30)
            )
        },
        trailingIcon = {
            if (isPasswordField) {
                IconButton(onClick = onShowPasswordClick) {
                    Icon(
                        imageVector = if (showPassword)
                            ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24)
                        else
                            ImageVector.vectorResource(id = R.drawable.baseline_visibility_24),
                        contentDescription = null,
                        modifier = Modifier.size(LocalDimen.current.iconSize30)
                    )
                }
            } else {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(LocalDimen.current.iconSize30)
                        .clickable(onClick = onTrailingIconClick)
                )
            }
        },
        visualTransformation = if (isPasswordField && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth()
    )
}