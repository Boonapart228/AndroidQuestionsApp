package com.balan.androidquestionsapp.presentation.score.components.contents

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun BottomBarScore(
    imageVector: ImageVector,
    onSortClick: () -> Unit
) {
            IconButton(onClick = onSortClick) {
                Icon(
                    imageVector = imageVector, null,
                    modifier = Modifier.size(LocalDimen.current.iconSize64)
                )
            }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBarScore(
        imageVector = Icons.Filled.KeyboardArrowUp
    ) { }
}