package com.balan.androidquestionsapp.domain.usecase.score

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ScoreRepository

class DeleteResultUseCase(
    private val scoreRepository: ScoreRepository
) {
    fun execute(user: User, level: QuestionLevel): List<User> {
        return scoreRepository.deleteResult(user = user, level = level)
    }
}