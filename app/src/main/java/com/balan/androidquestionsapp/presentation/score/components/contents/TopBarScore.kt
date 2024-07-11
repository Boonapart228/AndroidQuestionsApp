package com.balan.androidquestionsapp.presentation.score.components.contents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.domain.models.SortDirection
import com.balan.androidquestionsapp.presentation.score.components.ScoreState
import com.balan.androidquestionsapp.ui.theme.ButtonColor
import com.balan.androidquestionsapp.ui.theme.LocalColors
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScore(
    imageVector: ImageVector,
    state: ScoreState,
    onClick: () -> Unit,
    onToggleMenuClick: () -> Unit,
    onSelectOptionClick: (SortDirection) -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalColors.current.backGround,
            titleContentColor = LocalColors.current.black,
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
        },
        actions = {
            IconButton(onClick = onToggleMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(LocalDimen.current.iconSize64),
                    tint = ButtonColor
                )
            }
            DropdownMenu(
                expanded = state.menuExpanded,
                onDismissRequest = { state.menuExpanded }
            ) {
                SortDirection.entries.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = type.textId)) },
                        onClick = {
                            onSelectOptionClick(type)
                            onToggleMenuClick()
                        })
                }
            }
        }
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewTopBarScore() {
    TopBarScore(
        imageVector = Icons.Filled.Home,
        onSelectOptionClick = { },
        state = ScoreState(),
        onClick = {}, onToggleMenuClick = { })
}