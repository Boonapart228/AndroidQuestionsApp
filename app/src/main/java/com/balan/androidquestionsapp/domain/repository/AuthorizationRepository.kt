package com.balan.androidquestionsapp.domain.repository

interface AuthorizationRepository {
    fun authorization(login: String, password: String) : Boolean
}