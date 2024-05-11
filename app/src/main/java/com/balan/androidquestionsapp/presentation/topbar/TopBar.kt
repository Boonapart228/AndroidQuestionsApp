package com.balan.androidquestionsapp.presentation.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.ButtonColor
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onClick: () -> Unit,
    imageVector: ImageVector,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Background,
            titleContentColor = Color.Black,
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier
                        .size(LocalDimen.current.iconSize64),
                    tint = ButtonColor
                )
            }
        }
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewTopBar() {
    TopBar({}, imageVector = Icons.Filled.Home)
}