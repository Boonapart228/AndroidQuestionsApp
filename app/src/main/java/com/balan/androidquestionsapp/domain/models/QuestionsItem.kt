package com.balan.androidquestionsapp.domain.models


import com.google.gson.annotations.SerializedName

data class QuestionsItem(
    @SerializedName("answers") val answers: List<Answer>,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String
)