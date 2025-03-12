package com.balan.androidquestionsapp.presentation.sign_in.model

import com.balan.androidquestionsapp.domain.models.Validation

data class ValidationSignInResults(
    val emailValidation: Validation,
    val passwordValidation: Validation
)

