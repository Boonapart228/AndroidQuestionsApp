package com.balan.androidquestionsapp.domain.user

import com.balan.androidquestionsapp.domain.models.QuestionLevel

interface UserSession {
    fun setLevel(session: QuestionLevel)
    fun getLevel(): QuestionLevel
}