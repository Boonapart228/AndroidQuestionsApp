package com.balan.androidquestionsapp.presentation.main_screen.components

import com.balan.androidquestionsapp.presentation.main_screen.model.MainButtons
import com.balan.androidquestionsapp.presentation.main_screen.model.buttonResources

data class MainState(
    val mainButtons: List<MainButtons> = buttonResources
)