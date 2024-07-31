package com.balan.androidquestionsapp.data

import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.UserLocalSource
import com.balan.androidquestionsapp.domain.repository.UserValidator
import com.balan.androidquestionsapp.presentation.sign_in.model.ValidationSignInResults
import com.balan.androidquestionsapp.presentation.sign_up.model.ValidationSignUpResults

class UserValidatorImpl(
    private val userLocalSource: UserLocalSource,
) : UserValidator {

    companion object {
        const val MIN_PASSWORD_LENGTH = 8
        const val SPECIAL_CHARACTERS = "!@#$%^&*()-_=+{}[]|:;'<>,.?/~`"
        const val MIN_LENGTH_LOGIN = 3
    }

    override fun validateSignIn(password: String, email: String): Validation {
        val user = userLocalSource.getByEmail(email)
        user?.let {
            if (user.password != password) return Validation.INVALID_PASSWORD
        }
        if (user == null) {
            return Validation.EMAIL_NOT_FOUND
        }
        return Validation.VALID
    }


    override fun mapValidationSignInResult(result: Validation): ValidationSignInResults {
        val emailValidation = when (result) {
            Validation.EMAIL_NOT_FOUND -> Validation.EMAIL_NOT_FOUND
            else -> Validation.VALID
        }

        val passwordValidation = when (result) {
            Validation.INVALID_PASSWORD -> Validation.INVALID_PASSWORD
            else -> Validation.VALID
        }
        return ValidationSignInResults(
            emailValidation = emailValidation,
            passwordValidation = passwordValidation,
        )
    }


    override fun validateSignUp(user: User, secondPassword: String): Validation {
        val emailValidation = isEmailAvailableForRegistration(email = user.email)
        if (emailValidation != Validation.VALID) {
            return emailValidation
        }
        val loginValidation = validateLogin(user.name)
        if (loginValidation != Validation.VALID) {
            return loginValidation
        }
        return validatePassword(password = user.password, secondPassword = secondPassword)
    }

    private fun validatePassword(password: String, secondPassword: String): Validation {
        return when {
            password.length < MIN_PASSWORD_LENGTH -> Validation.TOO_SHORT
            password != secondPassword -> Validation.PASSWORD_DO_NOT_MATCH
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
        if (userLocalSource.isExists(email)) {
            return Validation.EMAIL_ALREADY_EXIST
        }

        return Validation.VALID
    }

    override fun mapValidationResult(result: Validation): ValidationSignUpResults {
        val emailValidation = when (result) {
            Validation.INVALID_EMAIL -> Validation.INVALID_EMAIL
            Validation.EMAIL_ALREADY_EXIST -> Validation.EMAIL_ALREADY_EXIST
            else -> Validation.VALID
        }

        val passwordValidation = when (result) {
            Validation.INVALID_ADMIN_PASSWORD -> Validation.INVALID_ADMIN_PASSWORD
            Validation.PASSWORD_DO_NOT_MATCH -> Validation.PASSWORD_DO_NOT_MATCH
            Validation.TOO_SHORT -> Validation.TOO_SHORT
            Validation.NO_UPPERCASE_LETTER -> Validation.NO_UPPERCASE_LETTER
            Validation.NO_LOWERCASE_LETTER -> Validation.NO_LOWERCASE_LETTER
            Validation.NO_DIGIT -> Validation.NO_DIGIT
            Validation.NO_SPECIAL_CHARACTER -> Validation.NO_SPECIAL_CHARACTER
            else -> Validation.VALID
        }

        val loginValidation = when (result) {
            Validation.EMPTY_LOGIN -> Validation.EMPTY_LOGIN
            Validation.TOO_SHORT_LOGIN -> Validation.TOO_SHORT_LOGIN
            Validation.INVALID_CHARACTERS_IN_LOGIN -> Validation.INVALID_CHARACTERS_IN_LOGIN
            else -> Validation.VALID
        }

        return ValidationSignUpResults(
            emailValidation = emailValidation,
            passwordValidation = passwordValidation,
            loginValidation = loginValidation
        )
    }

}