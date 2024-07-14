package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.UserLocalSource
import com.balan.androidquestionsapp.domain.repository.UserValidator

class UserValidatorImpl(
    private val userLocalSource: UserLocalSource
) : UserValidator {

    companion object {
        const val MIN_PASSWORD_LENGTH = 8
        const val SPECIAL_CHARACTERS = "!@#$%^&*()-_=+{}[]|:;'<>,.?/~`"
        const val MIN_LENGTH_LOGIN = 3
    }

    override fun validateSignUp(user: User): Validation {
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
            password.length < MIN_PASSWORD_LENGTH -> Validation.TOO_SHORT
            !password.any { it.isUpperCase() } -> Validation.NO_UPPERCASE_LETTER
            !password.any { it.isLowerCase() } -> Validation.NO_LOWERCASE_LETTER
            !password.any { it.isDigit() } -> Validation.NO_DIGIT
            !password.any { SPECIAL_CHARACTERS.contains(it) } -> Validation.NO_SPECIAL_CHARACTER
            else -> Validation.VALID
        }
    }

    private fun validateLogin(login: String): Validation {
        return when {
            login.isEmpty() -> Validation.EMPTY_LOGIN
            login.length < MIN_LENGTH_LOGIN -> Validation.TOO_SHORT_LOGIN
            !login.all { it.isLetterOrDigit() } -> Validation.INVALID_CHARACTERS_IN_LOGIN
            else -> Validation.VALID
        }
    }

    private fun isEmailAvailableForRegistration(email: String): Validation {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Validation.INVALID_EMAIL
        }
        if (userLocalSource.isUserInDatabase(email)) {
            return Validation.EMAIL_ALREADY_EXIST
        }

        return Validation.VALID
    }

}