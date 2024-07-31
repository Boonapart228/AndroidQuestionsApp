package com.balan.androidquestionsapp.presentation.score.components.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.balan.androidquestionsapp.domain.models.SortDirections

data class SortDirection(val type: SortDirections, val icon: ImageVector)

val sortDirection = listOf(
    SortDirection(type = SortDirections.DECREASING, icon = Icons.Default.KeyboardArrowDown),
    SortDirection(type = SortDirections.INCREASING, icon = Icons.Default.KeyboardArrowUp),
    SortDirection(type = SortDirections.NAME, icon = Icons.Default.Person),
)