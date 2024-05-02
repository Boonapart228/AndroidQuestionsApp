package com.balan.androidquestionsapp.domain.models

data class Users(
    val name: String,
    val password: String,
    val email: String,
    val questions : QuestionsScore
)