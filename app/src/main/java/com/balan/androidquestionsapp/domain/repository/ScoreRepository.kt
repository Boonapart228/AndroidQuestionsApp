package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User

interface ScoreRepository {
    fun getUsers(): List<User>
    fun deleteResult(user: User, level: QuestionLevel)

    fun sortByIncreasingScore(level: QuestionLevel)
    fun sortByDecreasingScore(level: QuestionLevel)
    fun sortByName(level: QuestionLevel)
}