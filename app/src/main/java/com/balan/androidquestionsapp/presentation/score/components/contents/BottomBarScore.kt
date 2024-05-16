package com.balan.androidquestionsapp.presentation.score.components.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun BottomBarScore(
    onSortByNameClick: () -> Unit,
    onSortByIncreasingScoreClick: () -> Unit,
    onSortByDecreasingScoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = LocalDimen.current.spacerPaddingVertical),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Absolute.SpaceAround
    ) {
        IconButton(onClick = onSortByIncreasingScoreClick) {
            Icon(
                Icons.Filled.KeyboardArrowUp, null,
                modifier = Modifier.size(LocalDimen.current.iconSize64)
            )
        }
        IconButton(onClick = onSortByDecreasingScoreClick) {
            Icon(
                Icons.Filled.KeyboardArrowDown, null,
                modifier = Modifier.size(LocalDimen.current.iconSize64)
            )
        }
        IconButton(onClick = onSortByNameClick) {
            Icon(
                Icons.Filled.AccountCircle, null,
                modifier = Modifier.size(LocalDimen.current.iconSize64)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBarScore(
        onSortByNameClick = { },
        onSortByIncreasingScoreClick = { },
        onSortByDecreasingScoreClick = { }
    )
}