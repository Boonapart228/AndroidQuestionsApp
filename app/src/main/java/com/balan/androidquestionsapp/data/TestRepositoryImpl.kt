package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.data.local.currentUser
import com.balan.androidquestionsapp.data.local.juniorQuestion
import com.balan.androidquestionsapp.data.local.localUsers
import com.balan.androidquestionsapp.data.local.middleQuestion
import com.balan.androidquestionsapp.data.local.seniorQuestion
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.google.gson.Gson

class TestRepositoryImpl : TestRepository {

    override fun getQuestions(session: QuestionLevel): List<QuestionsItem> {
        val gson = Gson()
        val json = when (session) {
            QuestionLevel.JUNIOR -> juniorQuestion
            QuestionLevel.MIDDLE -> middleQuestion
            QuestionLevel.SENIOR -> seniorQuestion
            else -> juniorQuestion
        }
        return gson.fromJson(json, Array<QuestionsItem>::class.java).toList()
    }

    override fun updateScore(score: Int, question: QuestionLevel) {


        when (question) {
            QuestionLevel.JUNIOR -> {
                currentUser = currentUser.copy(question = currentUser.question.copy(junior = score))
                localUsers.forEachIndexed { index, user ->
                    if (user.email == currentUser.email) {
                        localUsers[index] = user.copy(question = user.question.copy(junior = score))
                    }
                }
            }

            QuestionLevel.MIDDLE -> {
                currentUser =
                    currentUser.copy(question = currentUser.question.copy(middle = score))
                localUsers.forEachIndexed { index, user ->
                    if (user.email == currentUser.email) {
                        localUsers[index] = user.copy(question = user.question.copy(middle = score))
                    }
                }
            }

            QuestionLevel.SENIOR -> {
                currentUser =
                    currentUser.copy(question = currentUser.question.copy(senior = score))
                localUsers.forEachIndexed { index, user ->
                    if (user.email == currentUser.email) {
                        localUsers[index] = user.copy(question = user.question.copy(senior = score))
                    }
                }
            }

            else -> 0
        }
    }
}