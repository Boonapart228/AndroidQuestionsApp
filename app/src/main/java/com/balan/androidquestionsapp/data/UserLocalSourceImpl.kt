package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.database.dao.UserDao
import com.balan.androidquestionsapp.domain.database.toEntity
import com.balan.androidquestionsapp.domain.database.toUser
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class UserLocalSourceImpl(
    private val userDao: UserDao
) : UserLocalSource {
    override fun sortByDecreasingScore(): List<User> {
        return userDao.sortByDecreasingScore().map { userDbEntity -> userDbEntity.toUser() }
    }

    override fun sortByIncreasingScore(): List<User> {
        return userDao.sortByIncreasingScore().map { userDbEntity -> userDbEntity.toUser() }
    }

    override fun sortUserByName(): List<User> {
        return userDao.sortUserByName().map { userDbEntity -> userDbEntity.toUser() }
    }

    override fun findUser(email: String, password: String): User? {
        return userDao.findUser(email, password)?.toUser()
    }

    override fun getUserById(accountId: Long): User? {
        return userDao.getUserById(accountId = accountId)?.toUser()
    }

    override fun updateScore(user: User) {
        userDao.updateScore(userDbEntity = user.toEntity())
    }

    override fun createUser(user: User) {
        userDao.createUser(userDbEntity = user.toEntity())
    }

    override fun getAllUsers(): List<User> {
        return userDao.getAllUsers().map { userDbEntity -> userDbEntity.toUser() }
    }
}