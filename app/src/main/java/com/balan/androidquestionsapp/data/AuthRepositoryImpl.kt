package com.balan.androidquestionsapp.data

import android.util.Log
import com.balan.androidquestionsapp.data.local.localUsers
import com.balan.androidquestionsapp.domain.database.UserLocalSource
import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val userLocalSource: UserLocalSource // Room
) : AuthRepository {
    companion object {
        const val PASSWORD = "qwe"
    }

    override fun signIn(email: String, password: String): User? {
        val user =
            userLocalSource.getAllUsers().find { it.email == email && it.password == password }
        user?.let {
            return user
        }
        return null
    }

    override fun signUp(login: String, password: String, email: String): Validation {
        val newUser = User(
            id = 0,
            name = login,
            password = password,
            email = email,
            QuestionsScore(junior = 0, middle = 0, senior = 0)
        )
        val signUpResult = isEmailAvailableForRegistration(newUser = newUser)
        if (signUpResult == Validation.VALID) localUsers.add(newUser)
        if (signUpResult == Validation.VALID) userLocalSource.createUser(newUser) // room
        Log.d("BD", "$newUser") // room
        return signUpResult
    }

    private fun isEmailAvailableForRegistration(newUser: User): Validation {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(newUser.email).matches()) {
            return Validation.INVALID_EMAIL
        }

        if (localUsers.any { it.email == newUser.email }) {
            return Validation.EMAIL_ALREADY_EXIST
        }
        return Validation.VALID
    }

    override fun adminAccess(password: String): Boolean = password == PASSWORD
}
