package com.balan.androidquestionsapp.domain.usecase.validate

import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.UserValidator

class ValidateSignInUseCase(
    private val userValidator: UserValidator
) {
    fun execute(email: String, password: String): Validation {
        return userValidator.validateSignIn(password = password, email = email)
    }
}