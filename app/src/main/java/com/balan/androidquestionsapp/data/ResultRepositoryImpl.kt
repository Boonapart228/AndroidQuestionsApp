package com.balan.androidquestionsapp.data


import android.content.Context
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader


class ResultRepositoryImpl(private val context: Context) : ResultRepository {
    override fun setQuestionSize(question: QuestionLevel): Int {

        val gson = Gson()
        val fileName = when (question) {
            QuestionLevel.JUNIOR -> QuestionLevel.JUNIOR.path
            QuestionLevel.MIDDLE -> QuestionLevel.MIDDLE.path
            QuestionLevel.SENIOR -> QuestionLevel.SENIOR.path
            QuestionLevel.DEFAULT -> QuestionLevel.DEFAULT.path
        }
        val json = context.assets.open(fileName).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                reader.readText()
            }
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