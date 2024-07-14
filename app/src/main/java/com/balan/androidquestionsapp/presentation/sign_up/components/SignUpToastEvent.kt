package com.balan.androidquestionsapp.presentation.sign_up.components

sealed class SignUpToastEvent {
    data object SuccessRegistration : SignUpToastEvent()

}