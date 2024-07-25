package com.balan.androidquestionsapp.presentation.main_screen.components.contents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.balan.androidquestionsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMainContent(
    onClick: () -> Unit,
    imageVector: ImageVector,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.test_selection),
                style = MaterialTheme.typography.titleLarge,

                )
        },
        navigationIcon = {
            IconButton(
                onClick = onClick,
              modifier = Modifier.padding(start = 16.dp, end = 24.dp)
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
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
fun TopBarMainContentPreview() {
    TopBarMainContent(onClick = {}, imageVector = Icons.Filled.ArrowBack)
}