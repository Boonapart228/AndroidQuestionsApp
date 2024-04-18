package com.balan.androidquestionsapp.presentation.registration_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RegistrationContent(modifier : Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Екран реєстрації")
    }
}