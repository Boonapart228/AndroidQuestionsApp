package com.balan.androidquestionsapp.domain.models

import com.balan.androidquestionsapp.R

enum class Validation(val textResId: Int) {
    VALID(R.string.valid),
    INVALID(R.string.in_valid),
    INVALID_EMAIL(R.string.in_valid_email),
    EMAIL_ALREADY_EXIST(R.string.email_already_exists),
    INVALID_ADMIN_PASSWORD(R.string.in_valid_admin_password),
    TOO_SHORT(R.string.too_short),
    NO_UPPERCASE_LETTER(R.string.no_uppercase_letter),
    NO_LOWERCASE_LETTER(R.string.no_lowercase_letter),
    NO_DIGIT(R.string.no_digit),
    NO_SPECIAL_CHARACTER(R.string.no_special_character),
    EMPTY_LOGIN(R.string.empty_login),
    TOO_SHORT_LOGIN(R.string.too_short_login),
    INVALID_CHARACTERS_IN_LOGIN(R.string.invalid_characters_in_login),
    DEFAULT(R.string.valid)
}