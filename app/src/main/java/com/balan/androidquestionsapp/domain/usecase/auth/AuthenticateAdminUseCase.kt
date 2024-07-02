package com.balan.androidquestionsapp.domain.usecase.auth

import com.balan.androidquestionsapp.domain.repository.AuthRepository

class AuthenticateAdminUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(password : String) : Boolean{
       return authRepository.adminAccess(password = password)
    }
}