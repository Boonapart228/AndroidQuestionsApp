package com.balan.androidquestionsapp.presentation.sign_up.models

import com.balan.androidquestionsapp.domain.models.Validation

data class ValidationSignUpResults(
    val emailValidation: Validation,
    val passwordValidation: Validation,
    val loginValidation: Validation
)
