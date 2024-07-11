package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.domain.models.TestType
import com.balan.androidquestionsapp.presentation.main_screen.components.contents.TopBarMainContent
import com.balan.androidquestionsapp.presentation.main_screen.model.mainButtons
import com.balan.androidquestionsapp.ui.theme.LocalColors
import com.balan.androidquestionsapp.ui.theme.LocalDimen
import com.balan.androidquestionsapp.ui.theme.LocalProperty

@Composable
fun MainContent(
    onSignInClick: () -> Unit,
    onTestClick: (TestType) -> Unit,
    onTestDoubleClick: (TestType) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBarMainContent(
                onClick = onSignInClick,
                imageVector = Icons.Filled.ArrowBack
            )
        }
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .background(LocalColors.current.backGround)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.arrangementSpaceBy12)
            ) {
                items(mainButtons) { (testType, messageResId) ->
                    MainButton(
                        messageResId = messageResId,
                        onClick = { onTestClick(testType) },
                        onAdminDoubleClick = { onTestDoubleClick(testType) }
                    )
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
        val infiniteTransition = rememberInfiniteTransition(label = "")
        val sway by infiniteTransition.animateFloat(
            initialValue = LocalProperty.current.initialValueAnimation,
            targetValue = LocalProperty.current.targetValueAnimation,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = LocalProperty.current.durationMillisAnimation,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        Card {
            Text(
                text = stringResource(id = messageResId),
                fontSize = LocalDimen.current.textSizeQuestion,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .border(
                        width = LocalDimen.current.borderWidth2,
                        shape = RoundedCornerShape(LocalDimen.current.roundedCornerShape16),
                        color = LocalColors.current.black
                    )
                    .fillMaxWidth()
                    .graphicsLayer { rotationZ = sway }
            )
        }

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