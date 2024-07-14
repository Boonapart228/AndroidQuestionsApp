package com.balan.androidquestionsapp.presentation.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.ButtonColor
import com.balan.androidquestionsapp.ui.theme.LocalColors
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopBar(
    onClick: () -> Unit,
    imageVector: ImageVector,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LocalColors.current.background)
            .padding(LocalDimen.current.paddingAll16),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = {
                keyboardController?.hide()
                onClick()
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(LocalDimen.current.iconSize64),
                tint = ButtonColor
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewTopBar() {
    TopBar({}, imageVector = Icons.Filled.Home)
}