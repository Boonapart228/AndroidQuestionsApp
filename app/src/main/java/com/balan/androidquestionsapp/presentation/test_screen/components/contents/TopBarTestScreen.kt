package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.LocalDimen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTestScreen(
    count: Int,
    size: Int,
    onMainClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Background,
                titleContentColor = Color.Black,
            ),
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$count/$size",
                        modifier = Modifier.padding(top = LocalDimen.current.spacerPaddingTop4),
                        textAlign = TextAlign.Center,
                        fontSize = LocalDimen.current.textSize32
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onMainClick) {
                    Icon(
                        Icons.Filled.ArrowBack, null,
                        modifier = Modifier.size(LocalDimen.current.iconSize64)
                    )
                }
            }
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PreviewTopBar() {
    TopBarTestScreen(count = 0, size = 4, onMainClick = {})
}
