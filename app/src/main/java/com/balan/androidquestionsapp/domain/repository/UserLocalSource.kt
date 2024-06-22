package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.User


interface UserLocalSource {
    fun getUserById(accountId: Long): User?
    fun findUser(email: String, password: String): User?
    fun createUser(user: User)
    fun getAllUsers(): List<User>
    fun updateScore(user: User)
    fun sortUserByName(): List<User>
    fun sortByIncreasingScore(): List<User>
    fun sortByDecreasingScore(): List<User>
}
