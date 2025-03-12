package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ScoreRepository
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class ScoreRepositoryImpl(
    private val userLocalSource: UserLocalSource
) : ScoreRepository {

    override fun deleteResult(user: User, level: QuestionLevel): List<User> {
        val listUserDb = userLocalSource.getAll().toMutableList()
        val indexDb = listUserDb.indexOf(user)
        when (level) {
            QuestionLevel.JUNIOR ->
                listUserDb[indexDb] =
                    listUserDb[indexDb].copy(question = listUserDb[indexDb].question.copy(junior = null))

            QuestionLevel.MIDDLE -> listUserDb[indexDb] =
                listUserDb[indexDb].copy(question = listUserDb[indexDb].question.copy(middle = null))

            QuestionLevel.SENIOR -> listUserDb[indexDb] =
                listUserDb[indexDb].copy(question = listUserDb[indexDb].question.copy(senior = null))

            QuestionLevel.DEFAULT -> listUserDb[indexDb]
        }
        val userDb = listUserDb[indexDb]
        userLocalSource.updateScore(userDb)
        return listUserDb
    }
}