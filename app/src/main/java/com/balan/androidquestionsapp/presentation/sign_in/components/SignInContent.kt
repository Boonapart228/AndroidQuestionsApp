package com.balan.androidquestionsapp.presentation.sign_in.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier

            .fillMaxSize()
            .background(Background)
            .padding(LocalDimen.current.paddingAll16)
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(LocalDimen.current.iconSize130)
        )
        Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight16))
        Text(
            text = stringResource(id = R.string.authorization),
            textAlign = TextAlign.Center,
            fontSize = LocalDimen.current.textSize24,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = { Text(text = stringResource(id = R.string.input_email)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                    modifier = Modifier.size(LocalDimen.current.iconSize30)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight16))
        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.input_password)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_password_24),
                    contentDescription = null,
                    modifier = Modifier.size(LocalDimen.current.iconSize30)
                )
            },
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
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
        Button(
            onClick = onSignInClick,
            colors = ButtonDefaults.buttonColors(Color.Black),
            enabled = isFieldsNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(LocalDimen.current.buttonShape)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in),
                fontSize = LocalDimen.current.textSize16
            )
        }
        Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight8))
        Text(
            text = stringResource(id = R.string.either),
            fontSize = LocalDimen.current.textSize16
        )
        TextButton(onClick = onSignUpClick) {
            Text(
                text = stringResource(id = R.string.sign_up),
                color = Color.Black,
                fontSize = LocalDimen.current.textSize16,
                fontWeight = FontWeight.Bold
            )

        }
        Text(
            text = stringResource(id = state.validation.textResId),
            color = Color.Black,
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
        isFieldsNotEmpty = { false },
        onShowPasswordClick = {}

    )
}