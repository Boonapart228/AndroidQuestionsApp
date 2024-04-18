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
    val buttonTextSize: TextUnit = 24.sp,
    val buttonWidth : Dp = 160.dp,
    val textButtonTextSize: TextUnit = 24.sp,
    val textSize: TextUnit = 24.sp,
    val spaceBetween: Dp = 100.dp,
    val spacerPaddingTop64: Dp = 64.dp,
    val spacerPaddingTop32: Dp = 32.dp
)