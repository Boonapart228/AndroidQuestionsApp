package com.balan.androidquestionsapp.ui.theme

import androidx.compose.runtime.compositionLocalOf

val LocalProperty = compositionLocalOf {
    Property()
}

data class Property(
    val weight1: Float = 1f,
    val weight05: Float = 0.5f
)