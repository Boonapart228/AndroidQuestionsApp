package com.balan.androidquestionsapp.domain.usecase.auth

import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(
        login: String,
        password: String,
        confirmPassword: String,
        email: String
    ): Validation {
        return authRepository.signUp(
            login = login,
            password = password,
            confirmPassword = confirmPassword,
            email = email
        )
    }
}