package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.AuthRepository
import com.balan.androidquestionsapp.domain.repository.UserLocalSource
import com.balan.androidquestionsapp.domain.repository.UserValidator

class AuthRepositoryImpl(
    private val userLocalSource: UserLocalSource,
    private val userValidator: UserValidator
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

    override fun signUp(login: String, password: String, confirmPassword : String, email: String): Validation {
        val newUser = User(
            id = 0,
            name = login,
            password = password,
            email = email,
            QuestionsScore(junior = 10, middle = 10, senior = 10)
        )
        val signUpResult = userValidator.validateSignUp(user = newUser, confirmPassword = confirmPassword)
        if (signUpResult == Validation.VALID) userLocalSource.create(newUser)
        return signUpResult
    }

    override fun adminAccess(password: String): Boolean = password == PASSWORD
}
