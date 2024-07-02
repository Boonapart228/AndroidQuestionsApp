package com.balan.androidquestionsapp.presentation.admin.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.topbar.TopBar
import com.balan.androidquestionsapp.ui.theme.Background
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
        onMainClick = {}
    )
}

@Composable
fun AdminContent(
    state: AdminState,
    setPassword: (String) -> Unit,
    onPanelScoreClick: () -> Unit,
    onMainClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBar(onClick = onMainClick, imageVector = Icons.Filled.Home) }
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
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier.size(LocalDimen.current.iconSize130)
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight16))
            Text(
                text = stringResource(id = R.string.results_panel),
                textAlign = TextAlign.Center,
                fontSize = LocalDimen.current.textSize24,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
            TextFieldBox(
                value = state.password,
                text = stringResource(R.string.input_password),
                imageVector = ImageVector.vectorResource(R.drawable.baseline_password_24),
                onValueChange = setPassword
            )
            Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight32))
            Button(
                onClick = onPanelScoreClick,
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(LocalDimen.current.buttonShape)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    fontSize = LocalDimen.current.textSize16
                )
            }
        }
    }
}

@Composable
fun TextFieldBox(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    imageVector: ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = text) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(LocalDimen.current.iconSize30)
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}