package com.balan.androidquestionsapp.presentation.result.components

import com.balan.androidquestionsapp.R

data class ResultState(
    val score: Int = 0,
    val questionSize: Int = 0,
    val animationResult: Int = R.raw.animation_test_failed
)