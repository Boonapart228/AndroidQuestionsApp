package com.balan.androidquestionsapp.domain.usecase.user_session

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetByEmailUseCase(
    private val userLocalSource: UserLocalSource
) {
    suspend fun execute(email: String): User? = withContext(Dispatchers.IO) {
        return@withContext userLocalSource.getByEmail(email)
    }
}