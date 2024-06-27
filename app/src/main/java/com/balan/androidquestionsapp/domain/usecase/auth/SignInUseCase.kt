package com.balan.androidquestionsapp.domain.usecase.auth

import com.balan.androidquestionsapp.domain.repository.AuthRepository
import com.balan.androidquestionsapp.domain.user.UserSession


class SignInUseCase(
    private val authRepository: AuthRepository,
    private val userSession: UserSession,
) {
    fun execute(email: String, password: String): Boolean {
        val user = authRepository.signIn(email = email, password = password)
        user?.let {
            userSession.setUser(user)
            return true
        }
        return false
    }
}
