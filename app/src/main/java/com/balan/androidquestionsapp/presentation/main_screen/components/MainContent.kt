package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainContent(modifier : Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Екран авторизації")
    }
}