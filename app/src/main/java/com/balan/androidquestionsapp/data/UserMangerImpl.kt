package com.balan.androidquestionsapp.data

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.datastore.preferences.remove
import com.balan.androidquestionsapp.domain.user.UserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserMangerImpl(context: Context) : UserManager {
    private val dataStore = context.createDataStore(name = "user_prefs")

    companion object {
        val USER_EMAIL_KEY = preferencesKey<String>("USER_EMAIL")
    }

    override suspend fun storeUserEmail(email: String) {
        dataStore.edit {
            it[USER_EMAIL_KEY] = email
        }
    }

    override suspend fun getSaveEmail(): String {
        return userEmailFlow.first()
    }

    override suspend fun removeAutologin() {
            dataStore.edit { preferences ->
            preferences.remove(USER_EMAIL_KEY)
        }
    }

    private val userEmailFlow: Flow<String> = dataStore.data.map {
        it[USER_EMAIL_KEY] ?: ""
    }
}