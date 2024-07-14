package com.balan.androidquestionsapp.domain.usecase.test

import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.balan.androidquestionsapp.domain.user.UserSession

class GetQuestionTitleUseCase(
    private val testRepository: TestRepository,
    private val userSession: UserSession
) {
    companion object {
        const val SEPARATOR = "_"
    }

    fun execute(): String {
        return testRepository.getQuestionTitle(userSession.getLevel())
            .substringBefore(SEPARATOR)
    }
}