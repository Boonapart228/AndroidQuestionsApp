package com.balan.androidquestionsapp.presentation.main_screen.components

import com.balan.androidquestionsapp.domain.models.TestType
import com.balan.androidquestionsapp.presentation.main_screen.model.buttonResources

data class MainState(
    val mainButtons: List<TestType> = buttonResources
)