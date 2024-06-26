package com.balan.androidquestionsapp.domain.models

import com.balan.androidquestionsapp.R

enum class Validation(val textResId: Int) {
    VALID(R.string.valid),
    INVALID(R.string.in_valid),
    INVALID_EMAIL(R.string.in_valid_email),
    EMAIL_ALREADY_EXIST(R.string.email_already_exists),
    INVALID_ADMIN_PASSWORD(R.string.in_valid_admin_password)
}