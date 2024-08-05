package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.presentation.sign_in.model.ValidationSignInResults
import com.balan.androidquestionsapp.presentation.sign_up.models.ValidationSignUpResults

interface UserValidator {
    fun validateSignUp(user: User, confirmPassword: String): Validation
    fun mapValidationSignInResult(result: Validation): ValidationSignInResults
    fun validateSignIn(password: String, email: String): Validation
    fun mapValidationResult(result: Validation): ValidationSignUpResults

}