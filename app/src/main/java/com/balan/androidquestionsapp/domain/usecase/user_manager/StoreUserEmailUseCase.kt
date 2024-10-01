package com.balan.androidquestionsapp.domain.usecase.user_manager

import com.balan.androidquestionsapp.domain.user.UserManager

class StoreUserEmailUseCase(
    private val userManager: UserManager
) {
    suspend fun execute(email: String) {
        userManager.storeUserEmail(email)
    }
}