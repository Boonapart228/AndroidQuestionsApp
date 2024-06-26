package com.balan.androidquestionsapp.domain.usecase.auth

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.AuthRepository


class SignInUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(email: String, password: String): User? {
        return authRepository.signIn(email = email, password = password)
    }
}
