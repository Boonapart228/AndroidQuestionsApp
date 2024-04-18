package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.data.local.localUsers
import com.balan.androidquestionsapp.domain.repository.AuthorizationRepository

class AuthorizationRepositoryImpl : AuthorizationRepository {
    override fun authorization(login: String, password: String): Boolean {
        localUsers.forEach { if (login == it.login && password == it.password) return true }
        return false
    }
}
