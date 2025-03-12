package com.balan.androidquestionsapp.presentation.sign_in.util

import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.presentation.sign_in.model.ValidationSignInResults

fun Validation.mapToSignInResults(): ValidationSignInResults {
    val emailValidation = when (this) {
        Validation.EMAIL_NOT_FOUND -> Validation.EMAIL_NOT_FOUND
        else -> Validation.VALID
    }

    val passwordValidation = when (this) {
        Validation.INVALID_PASSWORD -> Validation.INVALID_PASSWORD
        else -> Validation.VALID
    }
    return ValidationSignInResults(
        emailValidation = emailValidation,
        passwordValidation = passwordValidation,
    )
}