package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.SortDirection
import com.balan.androidquestionsapp.domain.models.User


interface UserLocalSource {
    fun getById(accountId: Long): User?
    fun getByEmailAndPassword(email: String, password: String): User?
    fun isExists(email: String): Boolean
    fun create(user: User)
    fun getAll(): List<User>
    fun updateScore(user: User)
    fun sortByDirection(sortDirection: SortDirection): List<User>

}
