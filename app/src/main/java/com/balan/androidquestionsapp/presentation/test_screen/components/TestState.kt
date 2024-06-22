package com.balan.androidquestionsapp.presentation.test_screen.components

import com.balan.androidquestionsapp.domain.models.Answer
import com.balan.androidquestionsapp.domain.models.QuestionsItem

data class TestState(
    val questions: List<QuestionsItem> = listOf(),
    val questionNumber: Int = 0,
    val selectedRadioAnswer: Answer? = null,
    val answered: Boolean = false,
    val selectedCheckAnswer: List<Answer> = emptyList(),
    val writtenAnswer: String = "",
    val score: Int = 0
)