package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation

interface UserValidator {
    fun validateSignUp(user: User): Validation

}