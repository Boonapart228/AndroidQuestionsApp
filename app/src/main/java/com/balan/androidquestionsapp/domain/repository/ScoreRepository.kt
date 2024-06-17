package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User

interface ScoreRepository {
    fun deleteResult(user: User, level: QuestionLevel) : List<User>
    fun sortByIncreasingScore(level: QuestionLevel) : List<User>
    fun sortByDecreasingScore(level: QuestionLevel) : List<User>
    fun sortByName() : List<User>
}