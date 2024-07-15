package com.balan.androidquestionsapp.domain.models

import com.balan.androidquestionsapp.R

enum class TestType(val messageResId: Int) {
    JUNIOR(R.string.question_junior),
    MIDDLE(R.string.question_middle),
    SENIOR(R.string.question_senior)
}