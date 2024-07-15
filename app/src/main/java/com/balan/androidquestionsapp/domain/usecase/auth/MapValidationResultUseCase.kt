package com.balan.androidquestionsapp.domain.usecase.auth

import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.UserValidator
import com.balan.androidquestionsapp.presentation.sign_up.model.ValidationResults

class MapValidationResultUseCase(
    private val userValidator: UserValidator
) {
    fun execute(signUpResult : Validation) : ValidationResults{
       return userValidator.mapValidationResult(signUpResult)
    }
}