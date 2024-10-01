package com.balan.androidquestionsapp.domain.usecase.user_manager

import com.balan.androidquestionsapp.domain.user.UserManager

class RemoveAutoLoginUseCase(
    private val userManager: UserManager
) {
    suspend fun execute(){
        userManager.removeAutologin()
    }
}