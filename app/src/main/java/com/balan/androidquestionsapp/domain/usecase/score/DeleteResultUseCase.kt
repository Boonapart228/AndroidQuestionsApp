package com.balan.androidquestionsapp.domain.usecase.score

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ScoreRepository
import com.balan.androidquestionsapp.domain.user.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteResultUseCase(
    private val scoreRepository: ScoreRepository,
    private val userSession: UserSession
) {
    suspend fun execute(user: User): List<User> {
        return withContext(Dispatchers.IO) {
            scoreRepository.deleteResult(user = user, level = userSession.getLevel())
        }
    }
}