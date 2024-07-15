package com.balan.androidquestionsapp.presentation.sign_in.components

import com.balan.androidquestionsapp.domain.models.Validation

data class SignInState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = true,
    val fieldsIsNotEmpty : Boolean = false,
    val validation: Validation = Validation.VALID
)
