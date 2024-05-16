package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.data.local.localUsers
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ScoreRepository

class ScoreRepositoryImpl : ScoreRepository {

    override fun deleteResult(user: User, level: QuestionLevel) {
        val index = localUsers.indexOf(user)
        when (level) {
            QuestionLevel.JUNIOR -> localUsers[index] =
                localUsers[index].copy(question = localUsers[index].question.copy(junior = null))

            QuestionLevel.MIDDLE -> localUsers[index] =
                localUsers[index].copy(question = localUsers[index].question.copy(middle = null))

            QuestionLevel.SENIOR -> localUsers[index] =
                localUsers[index].copy(question = localUsers[index].question.copy(senior = null))

            QuestionLevel.DEFAULT -> 0
        }
    }

    override fun sortByIncreasingScore(level: QuestionLevel) {
        when (level) {
            QuestionLevel.JUNIOR -> localUsers.sortBy { it.question.junior }

            QuestionLevel.MIDDLE -> localUsers.sortBy { it.question.middle }

            QuestionLevel.SENIOR -> localUsers.sortBy { it.question.senior }

            QuestionLevel.DEFAULT -> 0
        }
    }

    override fun sortByDecreasingScore(level: QuestionLevel) {
        when (level) {
            QuestionLevel.JUNIOR -> localUsers.sortByDescending { it.question.junior }

            QuestionLevel.MIDDLE -> localUsers.sortByDescending { it.question.junior }

            QuestionLevel.SENIOR -> localUsers.sortByDescending { it.question.junior }

            QuestionLevel.DEFAULT -> 0
        }
    }

    override fun sortByName() {
        localUsers.sortBy { it.name }
    }
}