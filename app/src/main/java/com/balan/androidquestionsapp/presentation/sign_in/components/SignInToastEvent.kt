package com.balan.androidquestionsapp.presentation.sign_in.components

sealed class SignInToastEvent {
    data object SuccessAuthorization : SignInToastEvent()

}