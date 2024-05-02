package com.balan.androidquestionsapp.presentation.sign_up.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignUpNavigationEvent>()

    val event = _event.asSharedFlow()


    fun setLogin(login: String) {
        _state.update {
            it.copy(name = login)
        }
    }

    fun setPassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun setEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    fun onSignInClick() {
        viewModelScope.launch {
            _event.emit(SignUpNavigationEvent.NavigationSignIn)
        }
    }


    fun onSignUpClick() {
        viewModelScope.launch {
            val name = _state.value.name
            val password = _state.value.password
            val email = _state.value.email
            val signUpResult =
                authRepository.signUp(login = name, password = password, email = email)
            _state.update {
                it.copy(
                    valid = when (signUpResult) {
                        Validation.VALID -> Validation.VALID
                        Validation.INVALID_EMAIL -> Validation.INVALID_EMAIL
                        Validation.EMAIL_ALREADY_EXIST -> Validation.EMAIL_ALREADY_EXIST
                        else -> Validation.VALID
                    }
                )
            }
            if (signUpResult == Validation.VALID) _event.emit(SignUpNavigationEvent.NavigationSignIn)
        }
    }

}