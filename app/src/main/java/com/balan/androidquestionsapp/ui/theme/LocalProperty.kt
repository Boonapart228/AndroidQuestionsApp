package com.balan.androidquestionsapp.ui.theme

import androidx.compose.runtime.compositionLocalOf

val LocalProperty = compositionLocalOf {
    Property()
}

data class Property(
    val initialValueAnimation: Float = -10f,
    val targetValueAnimation: Float = 10f,
    val durationMillisAnimation: Int = 1400,
    val largeContentWeight: Float = 3f,
)