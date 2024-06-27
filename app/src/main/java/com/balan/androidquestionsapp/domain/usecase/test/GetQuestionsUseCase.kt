package com.balan.androidquestionsapp.domain.usecase.test

import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.balan.androidquestionsapp.domain.user.UserSession

class GetQuestionsUseCase(
    private val testRepository: TestRepository,
    private val userSession: UserSession
) {
    fun execute() : List<QuestionsItem>{
      return testRepository.getQuestions(userSession.getLevel())
    }
}