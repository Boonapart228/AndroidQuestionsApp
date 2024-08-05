package com.balan.androidquestionsapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LocalDimen = compositionLocalOf {
    Dimensions()
}

data class Dimensions(
    val outlinedTextWidth: Dp = 280.dp,
    val outlinedTextHeight: Dp = 70.dp,
    val outlinedTextSize: TextUnit = 24.sp,
    val outlinedShape: Dp = 32.dp,
    val outlinedClip: Dp = 32.dp,
    val buttonTextSize24: TextUnit = 24.sp,
    val buttonTextSize18: TextUnit = 18.sp,
    val buttonWidth: Dp = 160.dp,
    val buttonSignUpWidth: Dp = 260.dp,
    val buttonSize: Dp = 16.dp,
    val textButtonTextSize: TextUnit = 24.sp,
    val textSize24: TextUnit = 24.sp,
    val textSize32: TextUnit = 32.sp,
    val textSize64: TextUnit = 64.sp,
    val textPaddingAll16: Dp = 16.dp,
    val textSizeQuestion: TextUnit = 64.sp,
    val textResultShape: Dp = 64.dp,
    val spaceBetween: Dp = 100.dp,
    val spacerPaddingTop64: Dp = 64.dp,
    val spacerPaddingTop32: Dp = 32.dp,
    val spacerPaddingTop4: Dp = 4.dp,
    val spacerPaddingAll32: Dp = 32.dp,
    val spacerPaddingAll16: Dp = 16.dp,
    val spacerPaddingAll8: Dp = 8.dp,
    val spacerPaddingBottom16: Dp = 16.dp,
    val spacerPaddingVertical: Dp = 32.dp,
    val spacerHeight16: Dp = 16.dp,
    val spacerHeight4: Dp = 4.dp,
    val spacerWidth8: Dp = 8.dp,
    val spacerWidth4: Dp = 4.dp,
    val iconButtonSize: Dp = 64.dp,
    val iconSize40: Dp = 40.dp,
    val iconSize64: Dp = 64.dp,
    val iconButton: Dp = 36.dp,
    val buttonCirclePaddingAll: Dp = 16.dp,
    val buttonCircleSize: Dp = 40.dp,
    val questionTitleShape: Dp = 32.dp,
    val questionTitleClip: Dp = 32.dp,
    val questionPaddingHorizontal: Dp = 48.dp,
    val questionTitleTextSize: TextUnit = 24.sp,
    val questionTitlePaddingAll: Dp = 8.dp,
    val questionVerticalPadding: Dp = 4.dp,
    val answerHeight: Dp = 32.dp,
    val answerWidth: Dp = 160.dp,
    val answerClip: Dp = 16.dp,
    val answerTextSize: TextUnit = 16.sp,
    val scoreTextHorizontalPadding: Dp = 8.dp,
    val scoreCardClip: Dp = 16.dp,
    val horizontalPadding24: Dp = 24.dp,
    val scrollContainerHeight: Dp = 300.dp,
    val titleBorderShape: Dp = 8.dp,
    val titleBorderWidth: Dp = 2.dp,
    val titleBackgroundShape: Dp = 8.dp,
    val iconSize24: Dp = 24.dp,
    val spacerHeight32: Dp = 32.dp,
    val spacerHeight8: Dp = 8.dp,
    val iconSize130: Dp = 130.dp,
    val paddingAll16: Dp = 16.dp,
    val buttonShape: Dp = 10.dp,
    val textSize16: TextUnit = 16.sp,
    val arrangementSpaceBy12: Dp = 12.dp,
    val borderWidth2: Dp = 2.dp,
    val roundedCornerShape16: Dp = 16.dp,
    val driverThickness: Dp = 1.dp,
    val horizontalPadding8: Dp = 8.dp,
    val borderAnswerWidth: Dp = 1.dp,
    val shapeAnswer: Dp = 6.dp,
    val lottieAnimationSize: Dp = 200.dp,
    val horizontalRowSpace: Dp = 8.dp,
    val horizontalRowPadding: Dp = 16.dp,
    val verticalRowPadding: Dp = 8.dp,
    val iconStartPadding: Dp = 16.dp,
    val iconEndPadding: Dp = 24.dp,
    val iconDeleteEndPadding: Dp = 12.dp,
)
