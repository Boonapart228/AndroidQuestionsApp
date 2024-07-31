package com.balan.androidquestionsapp.domain.usecase.user_source

import com.balan.androidquestionsapp.domain.models.SortDirections
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class SortByDirectionUseCase(
    private val userLocalSource: UserLocalSource
) {
    fun execute(sortDirections: SortDirections) : List<User>{
       return userLocalSource.sortByDirection(sortDirections)
    }
}