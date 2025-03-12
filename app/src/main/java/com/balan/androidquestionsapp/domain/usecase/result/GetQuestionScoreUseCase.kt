package com.balan.androidquestionsapp.domain.usecase.result

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ResultRepository

class GetQuestionScoreUseCase(
    private val resultRepository: ResultRepository
) {
    fun execute(user: User, question: QuestionLevel): Int {
       return resultRepository.getQuestionScore(user = user, question = question)
    }
}