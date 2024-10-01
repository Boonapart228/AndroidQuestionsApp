package com.balan.androidquestionsapp.domain.user

interface UserManager {
    suspend fun storeUserEmail(email: String)
   suspend fun getSaveEmail() : String
   suspend fun removeAutologin()
}