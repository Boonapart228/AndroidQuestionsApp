package com.balan.androidquestionsapp.domain.repository

interface AssetManager {
    fun getJsonByFileName(name : String) : String
}