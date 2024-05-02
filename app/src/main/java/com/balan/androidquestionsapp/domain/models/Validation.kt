package com.balan.androidquestionsapp.domain.models

import com.balan.androidquestionsapp.R

enum class Validation(val text: Int) {
    VALID(R.string.valid),
    INVALID(R.string.in_valid),
    INVALID_EMAIL(R.string.in_valid_email),
    EMAIL_ALREADY_EXIST(R.string.in_valid_email)
}