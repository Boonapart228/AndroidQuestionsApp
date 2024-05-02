package com.balan.androidquestionsapp.presentation.sign_up.components

import com.balan.androidquestionsapp.domain.models.Validation

data class SignUpState(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val valid: Validation = Validation.VALID,
)