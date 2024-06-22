package com.balan.androidquestionsapp.data

import android.content.Context
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.repository.AssetManager
import java.io.BufferedReader
import java.io.InputStreamReader

class AssetManagerImpl(
    private val context: Context
) : AssetManager {
    override  fun getJsonByFileName(session: QuestionLevel): String {
        val fileName = when (session) {
            QuestionLevel.JUNIOR -> QuestionLevel.JUNIOR.path
            QuestionLevel.MIDDLE -> QuestionLevel.MIDDLE.path
            QuestionLevel.SENIOR -> QuestionLevel.SENIOR.path
            QuestionLevel.DEFAULT -> QuestionLevel.DEFAULT.path
        }
        return context.assets.open(fileName).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                reader.readText()
            }
        }
    }
}