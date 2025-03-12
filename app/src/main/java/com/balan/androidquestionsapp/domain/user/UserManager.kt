package com.balan.androidquestionsapp.domain.user

import kotlinx.coroutines.flow.Flow

interface UserManager {
    suspend fun storeUserEmail(email: String)
    suspend fun getSaveEmail(): Flow<String>
    suspend fun removeAutologin()
}