package com.balan.androidquestionsapp.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.balan.androidquestionsapp.R

enum class SortOption(val textId: Int, val icon: ImageVector) {
    INCREASING(
        textId = R.string.increasing,
        icon = Icons.Default.KeyboardArrowDown,
    ),
    DECREASING(textId = R.string.decreasing, icon = Icons.Default.KeyboardArrowUp),
    NAME(textId = R.string.name, icon = Icons.Default.Person),
}