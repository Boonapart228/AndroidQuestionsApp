package com.balan.androidquestionsapp.data

import android.util.Log
import com.balan.androidquestionsapp.data.local.currentQuestionLevel
import com.balan.androidquestionsapp.data.local.currentUser
import com.balan.androidquestionsapp.data.local.juniorQuestion
import com.balan.androidquestionsapp.data.local.middleQuestion
import com.balan.androidquestionsapp.data.local.seniorQuestion
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.google.gson.Gson

class TestRepositoryImpl : TestRepository {

    override fun getQuestions(): List<QuestionsItem> {
        val gson = Gson()
        val json = when (currentQuestionLevel) {
            QuestionLevel.JUNIOR -> juniorQuestion
            QuestionLevel.MIDDLE -> middleQuestion
            QuestionLevel.SENIOR -> seniorQuestion
        }
        return gson.fromJson(json, Array<QuestionsItem>::class.java).toList()
    }

    override fun updateScore(score: Int) {
        Log.d("Creat","${currentUser.questions.junior}")
        when (currentQuestionLevel) {
            QuestionLevel.JUNIOR -> currentUser.questions.junior = score
            QuestionLevel.MIDDLE -> currentUser.questions.middle = score
            QuestionLevel.SENIOR -> currentUser.questions.senior = score
        }
    }

}