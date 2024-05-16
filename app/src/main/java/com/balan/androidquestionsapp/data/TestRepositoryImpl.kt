package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.data.local.juniorQuestion
import com.balan.androidquestionsapp.data.local.localUsers
import com.balan.androidquestionsapp.data.local.middleQuestion
import com.balan.androidquestionsapp.data.local.seniorQuestion
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.models.User
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
    override fun updateScore(score: Int, user: User, question: QuestionLevel): User {
        val newUserInfo = when (question) {
            QuestionLevel.JUNIOR -> user.copy(question = user.question.copy(junior = score))
            QuestionLevel.MIDDLE -> user.copy(question = user.question.copy(middle = score))
            QuestionLevel.SENIOR -> user.copy(question = user.question.copy(senior = score))
            QuestionLevel.DEFAULT -> user
        }
        updateUsers(newUserInfo)
        return newUserInfo
    }

    private fun updateUsers(user: User) {
        localUsers.forEachIndexed { index, currentUser ->
            if (currentUser.email == user.email) {
                localUsers[index] = user
            }
        }
    }
}