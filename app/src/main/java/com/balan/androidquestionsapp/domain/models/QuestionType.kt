package com.balan.androidquestionsapp.domain.models

enum class QuestionType(val type : String) {
    TEXT_FIELD("textField"),
    RADIO_BUTTON("radioButtons"),
    CHECK_BOX("checkBox")
}