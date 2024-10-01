package com.balan.androidquestionsapp.domain.usecase.user_session

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class GetByEmailUseCase(
    private val userLocalSource: UserLocalSource
) {
    fun execute(email: String): User? {
        return userLocalSource.getByEmail(email)
    }
}