package com.balan.androidquestionsapp.domain.repository

import com.balan.androidquestionsapp.domain.models.QuestionLevel

interface AssetManager {
    fun getJsonByFileName(session : QuestionLevel) : String
}