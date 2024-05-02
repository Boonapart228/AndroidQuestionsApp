package com.balan.androidquestionsapp.domain.repository

interface ResultRepository {
    fun getQuestionSize(): Int
    fun getQuestionScore(): Int
}