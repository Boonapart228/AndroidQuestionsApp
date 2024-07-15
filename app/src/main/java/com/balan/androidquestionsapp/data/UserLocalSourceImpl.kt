package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.database.dao.UserDao
import com.balan.androidquestionsapp.domain.database.toEntity
import com.balan.androidquestionsapp.domain.database.toUser
import com.balan.androidquestionsapp.domain.models.SortDirection
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class UserLocalSourceImpl(
    private val userDao: UserDao
) : UserLocalSource {

    override fun sortByDirection(sortDirection: SortDirection): List<User> {
        return when (sortDirection) {
            SortDirection.INCREASING ->{
                userDao.sortByScore(true)
                    .map { userDbEntity -> userDbEntity.toUser() }
            }

            SortDirection.DECREASING ->{
                userDao.sortByScore(false)
                    .map { userDbEntity -> userDbEntity.toUser() }
            }

            SortDirection.NAME -> userDao.sortUserByName()
                .map { userDbEntity -> userDbEntity.toUser() }
        }
    }

    override fun getByEmailAndPassword(email: String, password: String): User? {
        return userDao.findUser(email, password)?.toUser()
    }

    override fun isExists(email: String): Boolean {
        return userDao.findUser(email)
    }

    override fun getById(accountId: Long): User? {
        return userDao.getUserById(accountId = accountId)?.toUser()
    }

    override fun updateScore(user: User) {
        userDao.updateScore(userEntity = user.toEntity())
    }

    override fun create(user: User) {
        userDao.createUser(userEntity = user.toEntity())
    }

    override fun getAll(): List<User> {
        return userDao.getAllUsers().map { userDbEntity -> userDbEntity.toUser() }
    }


}