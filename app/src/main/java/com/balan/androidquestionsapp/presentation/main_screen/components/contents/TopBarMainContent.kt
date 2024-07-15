package com.balan.androidquestionsapp.presentation.main_screen.components.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.ui.theme.LocalColors
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun TopBarMainContent(
    onClick: () -> Unit,
    imageVector: ImageVector,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LocalColors.current.background)
            .padding(LocalDimen.current.paddingAll16),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.test_selection),
            style = MaterialTheme.typography.titleLarge,
            color = LocalColors.current.uiElementBlack
        )

        IconButton(
            onClick = onClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(LocalDimen.current.iconSize64),
                tint = LocalColors.current.buttonColor
            )
        }
    }
}
