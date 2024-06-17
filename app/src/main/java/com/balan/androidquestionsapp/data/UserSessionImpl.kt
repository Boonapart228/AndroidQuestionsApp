package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.database.UserLocalSource
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.user.UserSession

class UserSessionImpl(
    private val userLocalSource: UserLocalSource
) : UserSession {
    private var currentLevel: QuestionLevel = QuestionLevel.DEFAULT
    private var currentAccount: User? = null

    override fun getUsers(): List<User> {
        return userLocalSource.getAllUsers()
    }

    override fun questionLevel(session: QuestionLevel) {
        currentLevel = session
    }

    override fun getLevel(): QuestionLevel {
        return currentLevel
    }

    override fun setUser(user: User) {
        currentAccount = user
    }

    override fun getCurrentUser(): User? {
        return currentAccount
    }

    override fun updateInfo(user: User) {
        currentAccount = user
        userLocalSource.updateScore(user)
    }
}

