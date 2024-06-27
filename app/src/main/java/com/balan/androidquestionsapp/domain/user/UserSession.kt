package com.balan.androidquestionsapp.domain.user

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User

interface UserSession {
    fun setQuestionLevel(session: QuestionLevel)
    fun getLevel(): QuestionLevel

    fun setUser(user: User)

    fun getCurrentUser(): User?

    fun updateInfo(user: User)
}