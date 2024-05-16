package com.balan.androidquestionsapp.presentation.result.components.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun ResultBottomBar(onMainClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onMainClick,
            modifier = Modifier.size(LocalDimen.current.iconButtonSize)
        ) {
            Icon(
                Icons.Filled.Home,
                contentDescription = null,
                modifier = Modifier
                    .size(LocalDimen.current.iconSize64)
                    .background(Color.White)
            )
        }
        Spacer(modifier = Modifier.padding(bottom = LocalDimen.current.spacerPaddingBottom16))
    }
}

@Preview
@Composable
fun PreviewResultBottomBar() {
    ResultBottomBar({})
}