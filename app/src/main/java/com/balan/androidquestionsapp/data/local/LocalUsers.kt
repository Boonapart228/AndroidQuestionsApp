package com.balan.androidquestionsapp.data.local

import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.User

val localUsers: MutableList<User> = mutableListOf(
    User(
        name = "Kolya", password = "123", email = "balan@gmail.com", QuestionsScore(
            junior = 10,
            middle = 6,
            senior = 10
        )
    ),
    User(
        name = "Dima", password = "546", email = "email@gmail.com", QuestionsScore(
            junior = 5,
            middle = 8,
            senior = 0
        )
    ),
    User(
        name = "Sasha", password = "798", email = "ema@gmail.com", QuestionsScore(
            junior = 8,
            middle = 8,
            senior = 0
        )
    )
)