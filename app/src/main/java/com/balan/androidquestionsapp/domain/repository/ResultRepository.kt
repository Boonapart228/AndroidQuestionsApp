package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.QuestionLevel

interface ResultRepository {
    fun getQuestionSize(question : QuestionLevel): Int
    fun getQuestionScore(question : QuestionLevel): Int?
}