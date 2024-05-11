package com.balan.androidquestionsapp.domain.models

data class User(
    val name: String,
    val password: String,
    val email: String,
    val question : QuestionsScore
)