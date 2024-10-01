package com.balan.androidquestionsapp.domain.usecase.user_manager

import com.balan.androidquestionsapp.domain.user.UserManager

class GetSaveEmailUseCase(
    private val userManager: UserManager
) {
    suspend fun execute(): String {
        return userManager.getSaveEmail()
    }
}