package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.data.local.currentUser
import com.balan.androidquestionsapp.data.local.juniorQuestion
import com.balan.androidquestionsapp.data.local.middleQuestion
import com.balan.androidquestionsapp.data.local.seniorQuestion
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.google.gson.Gson

class ResultRepositoryImpl() : ResultRepository {
    override fun getQuestionSize(question: QuestionLevel): Int {
        val gson = Gson()
        val json = when (question) {
            QuestionLevel.JUNIOR -> juniorQuestion
            QuestionLevel.MIDDLE -> middleQuestion
            QuestionLevel.SENIOR -> seniorQuestion
            else -> juniorQuestion
        }
        return gson.fromJson(json, Array<QuestionsItem>::class.java).size
    }


    override fun getQuestionScore(question: QuestionLevel): Int? {
        return when (question) {
            QuestionLevel.JUNIOR -> currentUser.question.junior
            QuestionLevel.MIDDLE -> currentUser.question.middle
            QuestionLevel.SENIOR -> currentUser.question.senior
            else -> 0
        }
    }
}