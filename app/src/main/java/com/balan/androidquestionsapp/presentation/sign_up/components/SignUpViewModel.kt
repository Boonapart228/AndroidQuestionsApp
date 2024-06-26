package com.balan.androidquestionsapp.presentation.sign_up.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: Provider<SignUpUseCase>
) : ViewModel() {

    private val _state: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignUpNavigationEvent>()

    val event = _event.asSharedFlow()


    fun setName(name: String) {
        _state.update {
            it.copy(name = name)
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
            _event.emit(SignUpNavigationEvent.NavigationToSignIn)
        }
    }


    fun onSignUpClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val name = _state.value.name
            val password = _state.value.password
            val email = _state.value.email
            val signUpResult = signUpUseCase.get().execute(login = name, password = password, email = email)
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
            if (signUpResult == Validation.VALID) _event.emit(SignUpNavigationEvent.NavigationToSignIn)
        }
    }

    fun isFieldsNotEmpty() = _state.value.name.isNotEmpty() &&
            _state.value.password.isNotEmpty() &&
            _state.value.email.isNotEmpty()

}