package com.balan.androidquestionsapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


val LocalColors = compositionLocalOf {
    Colors()
}

data class Colors(
    val green: Color = Color(0xFF00FF00),
    val red: Color = Color(0xFFFF0000),
    val black: Color = Color(0xFF000000),
    val white: Color = Color(0xFFFFFFFF),
    val backGround: Color = Color(0xFF9CECA9)
)