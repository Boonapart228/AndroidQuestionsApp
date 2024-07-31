package com.balan.androidquestionsapp.domain.usecase.validate

import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.UserValidator
import com.balan.androidquestionsapp.presentation.sign_in.model.ValidationSignInResults

class MapValidationSignInResultUseCase(
    private val userValidator: UserValidator
) {
    fun execute(signInResult: Validation) : ValidationSignInResults {
       return userValidator.mapValidationSignInResult(signInResult)
    }
}