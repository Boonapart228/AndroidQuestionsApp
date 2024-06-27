package com.balan.androidquestionsapp.domain.usecase.user_source

import com.balan.androidquestionsapp.domain.models.SortDirection
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class SortByDirectionUseCase(
    private val userLocalSource: UserLocalSource
) {
    fun execute(sortDirection: SortDirection) : List<User>{
       return userLocalSource.sortByDirection(sortDirection)
    }
}