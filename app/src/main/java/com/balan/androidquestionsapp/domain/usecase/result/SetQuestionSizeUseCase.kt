package com.balan.androidquestionsapp.domain.usecase.result

import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.repository.ResultRepository

class SetQuestionSizeUseCase(
    private val resultRepository: ResultRepository
) {
    fun execute(questionLevel: QuestionLevel): Int {
        return resultRepository.setQuestionSize(questionLevel)
    }
}