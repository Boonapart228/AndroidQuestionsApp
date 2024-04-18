package com.balan.androidquestionsapp.data.local

import com.balan.androidquestionsapp.domain.model.Users

val localUsers: List<Users> = listOf(
    Users(login = "Kolya", password = "123", email = "email1@gmail.com"),
    Users(login = "Dima", password = "546", email = "email2@gmail.com")
)