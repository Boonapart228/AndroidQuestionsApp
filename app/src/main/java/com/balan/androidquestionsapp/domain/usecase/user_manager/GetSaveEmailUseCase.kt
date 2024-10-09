package com.balan.androidquestionsapp.domain.usecase.user_manager

import com.balan.androidquestionsapp.domain.user.UserManager
import kotlinx.coroutines.flow.Flow

class GetSaveEmailUseCase(
    private val userManager: UserManager
) {
    suspend fun execute(): Flow<String> {
        return userManager.getSaveEmail()
    }
}