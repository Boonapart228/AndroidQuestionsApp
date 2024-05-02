package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.data.local.currentQuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.repository.MainRepository

class MainRepositoryImpl : MainRepository {
    override fun setJuniorQuestion() {
        currentQuestionLevel = QuestionLevel.JUNIOR
    }

    override fun setMiddleQuestion() {
        currentQuestionLevel = QuestionLevel.MIDDLE
    }

    override fun setSeniorQuestion() {
        currentQuestionLevel = QuestionLevel.SENIOR
    }
}