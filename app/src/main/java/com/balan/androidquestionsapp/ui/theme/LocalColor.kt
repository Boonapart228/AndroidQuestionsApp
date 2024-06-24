package com.balan.androidquestionsapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


val LocalColorResult = compositionLocalOf {
    ColorResult()
}

data class ColorResult(
    val green: Color = Color(0xFF00FF00),
    val red: Color = Color(0xFFFF0000)
)