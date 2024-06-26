package com.balan.androidquestionsapp.domain.usecase.user_session

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.user.UserSession

class QuestionLevelUseCase(
    private val userSession: UserSession
) {
    fun execute(questionLevel: QuestionLevel){
        userSession.questionLevel(questionLevel)
    }
}