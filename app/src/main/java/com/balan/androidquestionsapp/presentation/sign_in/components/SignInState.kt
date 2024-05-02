package com.balan.androidquestionsapp.presentation.sign_in.components

import com.balan.androidquestionsapp.domain.models.Validation

data class SignInState(
    val name: String = "",
    val password: String = "",
    val validation: Validation = Validation.VALID
)
