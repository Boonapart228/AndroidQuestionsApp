package com.balan.androidquestionsapp.presentation.sign_up.util

import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.presentation.sign_up.models.ValidationSignUpResults

fun Validation.mapToSignUpResults(): ValidationSignUpResults {
    val emailValidation = when (this) {
        Validation.INVALID_EMAIL -> Validation.INVALID_EMAIL
        Validation.EMAIL_ALREADY_EXIST -> Validation.EMAIL_ALREADY_EXIST
        else -> Validation.VALID
    }

    val passwordValidation = when (this) {
        Validation.INVALID_ADMIN_PASSWORD -> Validation.INVALID_ADMIN_PASSWORD
        Validation.PASSWORD_DO_NOT_MATCH -> Validation.PASSWORD_DO_NOT_MATCH
        Validation.TOO_SHORT -> Validation.TOO_SHORT
        Validation.NO_UPPERCASE_LETTER -> Validation.NO_UPPERCASE_LETTER
        Validation.NO_LOWERCASE_LETTER -> Validation.NO_LOWERCASE_LETTER
        Validation.NO_DIGIT -> Validation.NO_DIGIT
        Validation.NO_SPECIAL_CHARACTER -> Validation.NO_SPECIAL_CHARACTER
        else -> Validation.VALID
    }

    val loginValidation = when (this) {
        Validation.EMPTY_LOGIN -> Validation.EMPTY_LOGIN
        Validation.TOO_SHORT_LOGIN -> Validation.TOO_SHORT_LOGIN
        Validation.INVALID_CHARACTERS_IN_LOGIN -> Validation.INVALID_CHARACTERS_IN_LOGIN
        else -> Validation.VALID
    }

    return ValidationSignUpResults(
        emailValidation = emailValidation,
        passwordValidation = passwordValidation,
        loginValidation = loginValidation
    )
}