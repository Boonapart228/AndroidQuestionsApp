package com.balan.androidquestionsapp.domain.models

import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("isTrue") val isTrue: Boolean,
    @SerializedName("title") val title: String
)
