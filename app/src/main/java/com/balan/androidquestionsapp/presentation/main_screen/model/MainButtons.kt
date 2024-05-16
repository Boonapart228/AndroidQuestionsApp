package com.balan.androidquestionsapp.presentation.main_screen.model

import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.TestType

val mainButtons = listOf(
    TestType.JUNIOR to R.string.question_junior,
    TestType.MIDDLE to R.string.question_middle,
    TestType.SENIOR to R.string.question_senior
)