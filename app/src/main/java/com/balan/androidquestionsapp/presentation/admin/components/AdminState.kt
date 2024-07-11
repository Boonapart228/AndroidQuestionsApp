package com.balan.androidquestionsapp.presentation.admin.components

import com.balan.androidquestionsapp.domain.models.Validation

data class AdminState(
    val password: String = "",
    val isFieldsNotEmpty : Boolean = false,
    val passwordValidation: Validation = Validation.VALID
)