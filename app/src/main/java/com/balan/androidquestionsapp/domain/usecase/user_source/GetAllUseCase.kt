package com.balan.androidquestionsapp.domain.usecase.user_source

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class GetAllUseCase(
    private val userLocalSource: UserLocalSource
) {
    fun execute() : List<User>{
      return userLocalSource.getAll()
    }
}