package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.Validation

interface AuthRepository {
    fun signIn(login: String, password: String): Boolean
    fun signUp(login: String, password: String, email: String): Validation
}