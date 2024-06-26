package com.balan.androidquestionsapp.domain.usecase.test

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsItem
import com.balan.androidquestionsapp.domain.repository.TestRepository

class GetQuestionsUseCase(
    private val testRepository: TestRepository
) {
    fun execute(session : QuestionLevel) : List<QuestionsItem>{
      return testRepository.getQuestions(session)
    }
}