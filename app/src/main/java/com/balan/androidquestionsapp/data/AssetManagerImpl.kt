package com.balan.androidquestionsapp.data

import android.content.Context
import com.balan.androidquestionsapp.domain.repository.AssetManager
import java.io.BufferedReader
import java.io.InputStreamReader

class AssetManagerImpl(
    private val context: Context
) : AssetManager {
    override fun getJsonByFileName(name: String): String {
        return context.assets.open(name).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                reader.readText()
            }
        }
    }
}
