package com.balan.androidquestionsapp.presentation.score.components.contents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.SortOption
import com.balan.androidquestionsapp.presentation.score.components.ScoreState
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScore(
    state: ScoreState,
    onClick: () -> Unit,
    onToggleMenuClick: () -> Unit,
    onSelectOptionClick: (SortOption) -> Unit,
    onActiveSortOptionClick: (SortOption) -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(text = stringResource(id = R.string.score_screen)) },
        navigationIcon = {
            IconButton(
                onClick = onClick,
                modifier = Modifier.padding(
                    start = LocalDimen.current.iconStartPadding,
                    end = LocalDimen.current.iconEndPadding
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onToggleMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = state.menuExpanded,
                onDismissRequest = onToggleMenuClick
            ) {
                SortOption.entries.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.clickable {
                            onActiveSortOptionClick(item)
                            onSelectOptionClick(item)
                            onToggleMenuClick()
                        }
                    ) {
                        if (state.sortBy == item) {
                            Icon(
                                imageVector = Icons.Default.Check, contentDescription = null,
                                modifier = Modifier
                                    .padding(start = LocalDimen.current.iconStartPadding)
                                    .size(
                                        LocalDimen.current.buttonSelectedSortType
                                    )
                            )
                        }
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.padding(start = LocalDimen.current.iconStartPadding)
                        )
                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = item.textId)) },
                            onClick = {
                                onActiveSortOptionClick(item)
                                onSelectOptionClick(item)
                                onToggleMenuClick()
                            },
                        )
                    }
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
        onSelectOptionClick = { },
        state = ScoreState(),
        onClick = {}, onToggleMenuClick = { }, onActiveSortOptionClick = {})
}