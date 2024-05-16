package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User

interface ResultRepository {
    fun setQuestionSize(question : QuestionLevel): Int
    fun getQuestionScore(user : User, question : QuestionLevel): Int
}