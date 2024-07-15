package com.balan.androidquestionsapp.ui.theme

import androidx.compose.runtime.compositionLocalOf

val LocalProperty = compositionLocalOf {
    Property()
}

data class Property(
    val weight1: Float = 1f,
    val weight05: Float = 0.5f,
    val initialValueAnimation : Float = -10f,
    val targetValueAnimation : Float = 10f,
    val durationMillisAnimation : Int = 1400
)