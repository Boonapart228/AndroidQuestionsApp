package com.balan.androidquestionsapp.data.local

import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.Users

val localUsers: MutableList<Users> = mutableListOf(
    Users(
        name = "Kolya", password = "123", email = "balan@gmail.com", QuestionsScore(
            junior = 10,
            middle = 6,
            senior = 10
        )
    ),
    Users(
        name = "Dima", password = "546", email = "email@gmail.com", QuestionsScore(
            junior = 0,
            middle = 0,
            senior = 0
        )
    )
)