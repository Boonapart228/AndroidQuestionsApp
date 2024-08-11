package com.balan.androidquestionsapp.presentation.test_screen.components.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun BottomBar(
    answered: Boolean,
    enabled: Boolean,
    onIndexPlusClick: () -> Unit,
    onIndexMinusClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = LocalDimen.current.spacerPaddingVertical),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Absolute.SpaceAround
    ) {
        Button(
            onClick = onIndexMinusClick,
            modifier = Modifier.width(LocalDimen.current.buttonWidth),
            colors = ButtonDefaults.buttonColors(
            ),
            enabled = enabled
        ) {
            Text(
                text = stringResource(id = R.string.button_back),
                fontSize = LocalDimen.current.buttonTextSize18
            )
        }
        Button(
            onClick = onIndexPlusClick,
            modifier = Modifier.width(LocalDimen.current.buttonWidth),
            enabled = answered
        ) {
            Text(
                text = stringResource(id = R.string.button_next),
                fontSize = LocalDimen.current.buttonTextSize18
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun BottomBarPreview() {
    BottomBar(
        onIndexPlusClick = { }, onIndexMinusClick = { },
        answered = false,
        enabled = false,
    )
}