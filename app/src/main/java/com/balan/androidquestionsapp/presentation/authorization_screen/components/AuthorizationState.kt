package com.balan.androidquestionsapp.presentation.authorization_screen.components

import com.balan.androidquestionsapp.R

data class AuthorizationState(
    val login: String = "",
    val password: String = "",
    val access : Int = R.string.clear
)

