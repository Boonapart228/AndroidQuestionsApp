package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.TestType
import com.balan.androidquestionsapp.presentation.main_screen.model.mainButtons
import com.balan.androidquestionsapp.presentation.topbar.TopBar
import com.balan.androidquestionsapp.ui.theme.Background
import com.balan.androidquestionsapp.ui.theme.ButtonColor
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun MainContent(
    onSignInClick: () -> Unit,
    onTestClick: (TestType) -> Unit,
    onTestDoubleClick: (TestType) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                onClick = onSignInClick,
                imageVector = Icons.Filled.ArrowBack
            )
        }
    ) {
        Box(modifier = Modifier.padding(it))
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Background)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxSize()
            ) {

                Spacer(modifier = Modifier.padding(LocalDimen.current.spacerPaddingAll32))
                mainButtons.forEach { (testType, messageResId) ->
                    MainButton(
                        messageResId = messageResId,
                        onClick = { onTestClick(testType) },
                        onAdminDoubleClick = { onTestDoubleClick(testType) }
                    )
                    Spacer(modifier = Modifier.padding(LocalDimen.current.spacerPaddingAll32))
                }
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainButton(messageResId: Int, onAdminDoubleClick: () -> Unit, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.combinedClickable(
            onClick = onClick,
            onDoubleClick = onAdminDoubleClick
        )
    ) {
        Button(
            onClick = { return@Button },
            modifier = Modifier
                .padding(LocalDimen.current.buttonCirclePaddingAll)
                .size(LocalDimen.current.buttonCircleSize)
                .background(
                    shape = CircleShape,
                    color = ButtonColor
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor
            )
        ) {}
        Text(
            text = stringResource(id = messageResId),
            fontSize = LocalDimen.current.textSizeQuestion
        )
    }

}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MainContentPreview() {
    MainContent(
        onSignInClick = {},
        modifier = Modifier, onTestClick = { }, onTestDoubleClick = { },
    )
}