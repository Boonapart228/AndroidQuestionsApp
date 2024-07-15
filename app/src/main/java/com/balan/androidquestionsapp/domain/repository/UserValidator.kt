package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.presentation.sign_up.model.ValidationResults

interface UserValidator {
    fun validateSignUp(user: User): Validation
    fun mapValidationResult(result: Validation): ValidationResults

}