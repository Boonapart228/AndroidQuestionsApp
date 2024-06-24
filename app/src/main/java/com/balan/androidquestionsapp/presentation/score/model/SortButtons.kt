package com.balan.androidquestionsapp.presentation.score.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import com.balan.androidquestionsapp.domain.models.SortDirection

val sortButtons = listOf(
    Icons.Filled.KeyboardArrowUp to SortDirection.INCREASING,
    Icons.Filled.KeyboardArrowDown to SortDirection.DECREASING,
    Icons.Filled.AccountCircle to SortDirection.NAME,
)