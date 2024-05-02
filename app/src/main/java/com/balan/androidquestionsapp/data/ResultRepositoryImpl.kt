package com.balan.androidquestionsapp.data

import android.util.Log
import com.balan.androidquestionsapp.data.local.currentQuestionLevel
import com.balan.androidquestionsapp.data.local.currentUser
import com.balan.androidquestionsapp.data.local.juniorQuestion
import com.balan.androidquestionsapp.data.local.middleQuestion
import com.balan.androidquestionsapp.data.local.seniorQuestion
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.google.gson.Gson

class ResultRepositoryImpl() : ResultRepository {
    override fun getQuestionSize(): Int {
        val gson = Gson()
        val json = when (currentQuestionLevel) {
            QuestionLevel.JUNIOR -> juniorQuestion
            QuestionLevel.MIDDLE -> middleQuestion
            QuestionLevel.SENIOR -> seniorQuestion
        }
        val sizeQuestion = gson.fromJson(json, Array<QuestionsItem>::class.java).toList()
        return sizeQuestion.size
    }

    override fun getQuestionScore(): Int {
        Log.d("Creat", "!!! ${currentUser.questions.junior}")
        Log.d("Creat", "!!! ${currentUser.email}")

        return when (currentQuestionLevel) {
            QuestionLevel.JUNIOR -> currentUser.questions.junior
            QuestionLevel.MIDDLE -> currentUser.questions.middle
            QuestionLevel.SENIOR -> currentUser.questions.senior
        }
    }
}