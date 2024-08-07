package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.LocalDimen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTest(
    count: Int,
    size: Int,
    title: String,
    onMainClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = title, fontSize = LocalDimen.current.textSize24)
                    Text(
                        text = "$count/$size",
                        textAlign = TextAlign.Center,
                        fontSize = LocalDimen.current.textSize32
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onMainClick) {
                    Icon(
                        Icons.Filled.ArrowBack, null,
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
    TopBarTest(count = 0, size = 4, title = "JUNIOR", onMainClick = {})
}
