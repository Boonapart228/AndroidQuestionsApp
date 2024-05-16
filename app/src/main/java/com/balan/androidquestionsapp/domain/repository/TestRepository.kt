package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.models.User

interface TestRepository {
    fun getQuestions(session: QuestionLevel): List<QuestionsItem>
    fun updateScore(score: Int, user: User, question: QuestionLevel): User
}