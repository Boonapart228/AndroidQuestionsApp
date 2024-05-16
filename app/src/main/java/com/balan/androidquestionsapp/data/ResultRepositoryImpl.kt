package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.data.local.juniorQuestion
import com.balan.androidquestionsapp.data.local.middleQuestion
import com.balan.androidquestionsapp.data.local.seniorQuestion
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.google.gson.Gson

class ResultRepositoryImpl() : ResultRepository {
    override fun setQuestionSize(question: QuestionLevel): Int {
        val gson = Gson()
        val json = when (question) {
            QuestionLevel.JUNIOR -> juniorQuestion
            QuestionLevel.MIDDLE -> middleQuestion
            QuestionLevel.SENIOR -> seniorQuestion
            else -> juniorQuestion
        }
        return gson.fromJson(json, Array<QuestionsItem>::class.java).size
    }

    override fun getQuestionScore(user: User, question: QuestionLevel): Int {
        return when (question) {
            QuestionLevel.JUNIOR -> user.question.junior ?: 0
            QuestionLevel.MIDDLE -> user.question.middle ?: 0
            QuestionLevel.SENIOR -> user.question.senior ?: 0
            else -> 0
        }
    }
}