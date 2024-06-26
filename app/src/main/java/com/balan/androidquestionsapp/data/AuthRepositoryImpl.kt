package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.AuthRepository
import com.balan.androidquestionsapp.domain.repository.UserLocalSource

class AuthRepositoryImpl(
    private val userLocalSource: UserLocalSource
) : AuthRepository {
    companion object {
        const val PASSWORD = "qwe"
    }

    override fun signIn(email: String, password: String): User? {
        val user =
            userLocalSource.getByEmailAndPassword(email = email, password = password)
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
            QuestionsScore(junior = 10, middle = 10, senior = 10)
        )
        val signUpResult = isEmailAvailableForRegistration(newUser = newUser)
        if (signUpResult == Validation.VALID) userLocalSource.create(newUser)
        return signUpResult
    }

    private fun isEmailAvailableForRegistration(newUser: User): Validation {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(newUser.email).matches()) {
            return Validation.INVALID_EMAIL
        }
        if (userLocalSource.getByEmail(newUser.email)) {
            return Validation.EMAIL_ALREADY_EXIST
        }

        return Validation.VALID
    }

    override fun adminAccess(password: String): Boolean = password == PASSWORD
}
