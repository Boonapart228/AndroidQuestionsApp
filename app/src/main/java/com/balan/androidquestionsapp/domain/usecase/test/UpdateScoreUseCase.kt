package com.balan.androidquestionsapp.domain.usecase.test

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.TestRepository

class UpdateScoreUseCase(
    private val testRepository: TestRepository
) {
    fun execute(score: Int, user: User, question: QuestionLevel) : User {
       return testRepository.updateScore(score = score, user = user, question = question)
    }
}