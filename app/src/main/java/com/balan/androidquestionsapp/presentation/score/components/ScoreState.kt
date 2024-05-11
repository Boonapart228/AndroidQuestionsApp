package com.balan.androidquestionsapp.presentation.score.components

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User

data class ScoreState(
    val level: QuestionLevel = QuestionLevel.DEFAULT,
    val users: List<User> = emptyList()
) {
}