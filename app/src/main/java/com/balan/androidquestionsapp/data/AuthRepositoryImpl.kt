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
        val signUpResult = validateSignUp(user = newUser)
        if (signUpResult == Validation.VALID) userLocalSource.create(newUser)
        return signUpResult
    }


    private fun validateSignUp(user: User): Validation {
        val emailValidation = isEmailAvailableForRegistration(email = user.email)
        if (emailValidation != Validation.VALID) {
            return emailValidation
        }
        val loginValidation = validateLogin(user.name)
        if (loginValidation != Validation.VALID) {
            return loginValidation
        }
        return validatePassword(password = user.password)
    }

    private fun validatePassword(password: String): Validation {
        return when {
            password.length < 8 -> Validation.TOO_SHORT
            !password.any { it.isUpperCase() } -> Validation.NO_UPPERCASE_LETTER
            !password.any { it.isLowerCase() } -> Validation.NO_LOWERCASE_LETTER
            !password.any { it.isDigit() } -> Validation.NO_DIGIT
            !password.any { "!@#$%^&*()-_=+{}[]|:;'<>,.?/~`".contains(it) } -> Validation.NO_SPECIAL_CHARACTER
            else -> Validation.VALID
        }
    }

    private fun validateLogin(login: String): Validation {
        return when {
            login.isEmpty() -> Validation.EMPTY_LOGIN
            login.length < 3 -> Validation.TOO_SHORT_LOGIN
            !login.all { it.isLetterOrDigit() } -> Validation.INVALID_CHARACTERS_IN_LOGIN
            else -> Validation.VALID
        }
    }

    private fun isEmailAvailableForRegistration(email: String): Validation {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Validation.INVALID_EMAIL
        }
        if (userLocalSource.getByEmail(email)) {
            return Validation.EMAIL_ALREADY_EXIST
        }

        return Validation.VALID
    }

    override fun adminAccess(password: String): Boolean = password == PASSWORD
}
