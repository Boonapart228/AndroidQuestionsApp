package com.balan.androidquestionsapp.presentation.sign_up.components

import com.balan.androidquestionsapp.domain.models.Validation

data class SignUpState(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val secondaryPassword: String = "",
    val fieldsIsNotEmpty : Boolean = false,
    val passwordValidation: Validation = Validation.DEFAULT,
    val emailValidation: Validation = Validation.DEFAULT,
    val loginValidation: Validation = Validation.DEFAULT
)