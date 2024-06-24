package com.balan.androidquestionsapp.data


import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.UserLocalSource
import com.balan.androidquestionsapp.domain.user.UserSession

class UserSessionImpl(
    private val userLocalSource: UserLocalSource
) : UserSession {
    private var currentLevel: QuestionLevel = QuestionLevel.DEFAULT
    private var user: User? = null



    override fun questionLevel(session: QuestionLevel) {
        currentLevel = session
    }

    override fun getLevel(): QuestionLevel {
        return currentLevel
    }

    override fun setUser(user: User) {
        this.user = user
    }

    override fun getCurrentUser(): User? {
        return user
    }

    override fun updateInfo(user: User) {
        this.user = user
        userLocalSource.updateScore(user)
    }
}

