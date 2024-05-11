package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem

interface TestRepository {
    fun getQuestions(session : QuestionLevel): List<QuestionsItem>
    fun updateScore(score : Int, question: QuestionLevel)
}