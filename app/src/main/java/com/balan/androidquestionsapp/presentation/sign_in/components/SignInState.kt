package com.balan.androidquestionsapp.presentation.sign_in.components

import com.balan.androidquestionsapp.domain.models.Validation

data class SignInState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val fieldsIsNotEmpty: Boolean = false,
    val validationPassword: Validation = Validation.DEFAULT,
    val validationEmail: Validation = Validation.DEFAULT,
    val isExitDialogVisible : Boolean = false
)
