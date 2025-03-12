package com.balan.androidquestionsapp.domain.usecase.user_session

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.user.UserSession

class GetCurrentUserUseCase(
    private val userSession: UserSession
) {
    fun execute() : User?{
       return userSession.getCurrentUser()
    }
}