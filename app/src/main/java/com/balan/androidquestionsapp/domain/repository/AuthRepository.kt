package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation

interface AuthRepository {
    fun signIn(email: String, password: String): User?
    fun adminAccess(password: String): Boolean
    fun signUp(login: String, password: String, secondPassword : String,email: String): Validation
}