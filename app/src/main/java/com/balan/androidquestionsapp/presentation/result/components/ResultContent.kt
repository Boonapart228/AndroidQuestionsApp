package com.balan.androidquestionsapp.presentation.result.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.presentation.result.components.contents.TopBarResult
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewResultContent() {
    ResultContent(onMainClick = {}, state = ResultState())
}


@Composable
fun ResultContent(
    state: ResultState,
    onMainClick: () -> Unit,
) {

    val composition by rememberLottieComposition(
        if (state.animationResult != null) {
            LottieCompositionSpec.RawRes(
                state.animationResult
            )
        } else {
            LottieCompositionSpec.RawRes(
                R.raw.animation_not_found
            )
        }

    )

    BackHandler {
        onMainClick()
    }
    Scaffold(topBar = { TopBarResult() }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                LocalDimen.current.paddingAll16,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(LocalDimen.current.paddingAll16),
        ) {
            LottieAnimation(
                composition = composition,
                isPlaying = true,
                modifier = Modifier.size(LocalDimen.current.lottieAnimationSize)
            )
            Text(
                "${state.score}/${state.questionSize}",
                fontSize = LocalDimen.current.textSize64
            )
            Button(
                onClick = onMainClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(LocalDimen.current.buttonShape)
            ) {
                Text(
                    text = stringResource(id = R.string.menu),
                    fontSize = LocalDimen.current.textSize16,
                )
            }
        }

    }

}