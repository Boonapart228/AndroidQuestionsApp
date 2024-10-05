package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.database.dao.UserDao
import com.balan.androidquestionsapp.domain.database.toEntity
import com.balan.androidquestionsapp.domain.database.toUser
import com.balan.androidquestionsapp.domain.models.SortOption
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class UserLocalSourceImpl(
    private val userDao: UserDao
) : UserLocalSource {

    override fun getByEmail(email: String): User? {
        return userDao.getByEmail(email = email)?.toUser()
    }

    override fun getUsersSortedByOption(sortOption: SortOption): List<User> {
        return when (sortOption) {
            SortOption.INCREASING -> {
                userDao.getUsersSortedByScore(true)
                    .map { userDbEntity -> userDbEntity.toUser() }
            }

            SortOption.DECREASING -> {
                userDao.getUsersSortedByScore(false)
                    .map { userDbEntity -> userDbEntity.toUser() }
            }

            SortOption.NAME -> userDao.getUsersSortedByName()
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