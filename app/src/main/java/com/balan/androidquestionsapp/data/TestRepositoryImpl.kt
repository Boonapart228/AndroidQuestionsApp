package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.AssetManager
import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.google.gson.Gson

class TestRepositoryImpl(
    private val assetManager: AssetManager
) : TestRepository {
    override fun getQuestions(session: QuestionLevel): List<QuestionsItem> {
        val gson = Gson()
        val json = assetManager.getJsonByFileName(session.path)
        return gson.fromJson(json, Array<QuestionsItem>::class.java).toList()
    }

    override fun updateScore(score: Int, user: User, question: QuestionLevel): User {
        val newUserInfo = when (question) {
            QuestionLevel.JUNIOR -> user.copy(question = user.question.copy(junior = score))
            QuestionLevel.MIDDLE -> user.copy(question = user.question.copy(middle = score))
            QuestionLevel.SENIOR -> user.copy(question = user.question.copy(senior = score))
            QuestionLevel.DEFAULT -> user
        }
        return newUserInfo
    }

    override fun getQuestionTitle(question: QuestionLevel) = question.path.substringBefore("_")

}