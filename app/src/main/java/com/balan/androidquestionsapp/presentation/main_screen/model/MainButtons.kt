package com.balan.androidquestionsapp.presentation.main_screen.model

import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.TestType

val buttonResources = listOf(
    MainButtons(TestType.JUNIOR, R.string.question_junior),
    MainButtons(TestType.MIDDLE, R.string.question_middle),
    MainButtons(TestType.SENIOR, R.string.question_senior),
)

data class MainButtons(val testType: TestType, val messageResId: Int)