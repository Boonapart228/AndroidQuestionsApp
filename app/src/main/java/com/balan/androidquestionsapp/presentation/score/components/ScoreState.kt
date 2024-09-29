package com.balan.androidquestionsapp.presentation.score.components

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.SortOption
import com.balan.androidquestionsapp.domain.models.User

data class ScoreState(
    val level: QuestionLevel = QuestionLevel.DEFAULT,
    val users: List<User> = emptyList(),
    val user: User? = null,
    val menuExpanded: Boolean = false,
    val isDeleteDialogVisible: Boolean = false,
    val isLoading: Boolean = false,
    val sortDirections: List<SortOption> = SortOption.entries,
    val sortBy: SortOption = SortOption.INCREASING,
)