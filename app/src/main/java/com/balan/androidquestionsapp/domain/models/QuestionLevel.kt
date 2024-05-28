package com.balan.androidquestionsapp.domain.models

enum class QuestionLevel(val path: String) {
    JUNIOR("junior_question.json"),
    MIDDLE("middle_question.json"),
    SENIOR("senior_question.json"),
    DEFAULT("default.json")
}