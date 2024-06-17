package com.balan.androidquestionsapp.data.local

import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.User

val localUsers: MutableList<User> = mutableListOf(
    User(
        id = 0, name = "Kolya", password = "123", email = "balan@gmail.com", QuestionsScore(
            junior = 10,
            middle = 6,
            senior = 10
        )
    )
)