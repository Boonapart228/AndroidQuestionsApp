package com.balan.androidquestionsapp.presentation.score.components

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User

data class ScoreState(
    val level: QuestionLevel = QuestionLevel.DEFAULT,
    val users: List<User> = emptyList(),
    val user: User? = null,
    val menuExpanded: Boolean = false,
    val dialogAlert: Boolean = false,
    val dialogAction: Boolean = false
)