package com.balan.androidquestionsapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


val LocalColors = compositionLocalOf {
    Colors()
}

data class Colors(
    val testPassedGreen: Color = Color(0xFF00FF00),
    val warningRed: Color = Color(0xFFFF0000),


)