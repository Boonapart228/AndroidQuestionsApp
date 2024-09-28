package com.balan.androidquestionsapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


val LocalColors = compositionLocalOf {
    Colors()
}

data class Colors(
    val testPassedGreen: Color = Color(0xBF00FF00),
    val testFailedRed: Color = Color(0xBEFF0000),
)