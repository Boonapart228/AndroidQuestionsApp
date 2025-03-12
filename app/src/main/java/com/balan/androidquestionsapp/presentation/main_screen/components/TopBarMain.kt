package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMain(
    onClick: () -> Unit,
    text: String,
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,

                )
        },
        navigationIcon = {
            IconButton(
                onClick = onClick,
                modifier = Modifier.padding(
                    start = LocalDimen.current.iconStartPadding,
                    end = LocalDimen.current.iconEndPadding
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_logout_24),
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TopBarPreview() {
    TopBarMain(onClick = {}, text = "Топ")
}