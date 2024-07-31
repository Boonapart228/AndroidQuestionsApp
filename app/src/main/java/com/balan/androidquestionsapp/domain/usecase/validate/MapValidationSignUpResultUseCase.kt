package com.balan.androidquestionsapp.domain.usecase.validate

import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.UserValidator
import com.balan.androidquestionsapp.presentation.sign_up.model.ValidationSignUpResults

class MapValidationSignUpResultUseCase(
    private val userValidator: UserValidator
) {
    fun execute(signUpResult : Validation) : ValidationSignUpResults{
       return userValidator.mapValidationResult(signUpResult)
    }
}