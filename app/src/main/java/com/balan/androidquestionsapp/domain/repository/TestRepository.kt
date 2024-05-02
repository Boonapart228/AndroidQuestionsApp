package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.QuestionsItem

interface TestRepository {
    fun getQuestions(): List<QuestionsItem>
    fun updateScore(score : Int)
}