package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.database.UserLocalSource
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ScoreRepository

class ScoreRepositoryImpl(
    private val userLocalSource: UserLocalSource
) : ScoreRepository {

    override fun deleteResult(user: User, level: QuestionLevel): List<User> {
        val listUserDb = userLocalSource.getAllUsers().toMutableList()
        val indexDb = listUserDb.indexOf(user)
        when (level) {
            QuestionLevel.JUNIOR ->
                listUserDb[indexDb] =
                    listUserDb[indexDb].copy(question = listUserDb[indexDb].question.copy(junior = null))

            QuestionLevel.MIDDLE -> listUserDb[indexDb] =
                listUserDb[indexDb].copy(question = listUserDb[indexDb].question.copy(middle = null))

            QuestionLevel.SENIOR -> listUserDb[indexDb] =
                listUserDb[indexDb].copy(question = listUserDb[indexDb].question.copy(senior = null))

            QuestionLevel.DEFAULT -> 0
        }
        val userDb = listUserDb[indexDb]
        userLocalSource.updateScore(userDb)
        return listUserDb
    }

    override fun sortByIncreasingScore(level: QuestionLevel): List<User> {
        val userList = when (level) {
            QuestionLevel.JUNIOR -> userLocalSource.getAllUsers().sortedBy { it.question.junior }

            QuestionLevel.MIDDLE -> userLocalSource.getAllUsers().sortedBy { it.question.middle }

            QuestionLevel.SENIOR -> userLocalSource.getAllUsers().sortedBy { it.question.senior }

            QuestionLevel.DEFAULT -> userLocalSource.getAllUsers()
        }
        return userList
    }

    override fun sortByDecreasingScore(level: QuestionLevel): List<User> {
        val userList = when (level) {
            QuestionLevel.JUNIOR -> userLocalSource.getAllUsers()
                .sortedByDescending { it.question.junior }

            QuestionLevel.MIDDLE -> userLocalSource.getAllUsers()
                .sortedByDescending { it.question.middle }

            QuestionLevel.SENIOR -> userLocalSource.getAllUsers()
                .sortedByDescending { it.question.senior }

            QuestionLevel.DEFAULT -> userLocalSource.getAllUsers()
        }
        return userList
    }

    override fun sortByName(): List<User> {
        return userLocalSource.getAllUsers().sortedBy { it.name }
    }
}