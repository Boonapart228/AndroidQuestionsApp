package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.user.UserSession

class UserSessionImpl : UserSession {
    private var currentLevel: QuestionLevel = QuestionLevel.DEFAULT
    override fun setLevel(session: QuestionLevel) {
        currentLevel = session
    }

    override fun getLevel(): QuestionLevel {
        return currentLevel
    }
}

