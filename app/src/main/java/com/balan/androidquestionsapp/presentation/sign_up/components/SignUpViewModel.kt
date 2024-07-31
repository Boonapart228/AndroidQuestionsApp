package com.balan.androidquestionsapp.presentation.sign_up.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.InputFieldType
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.usecase.auth.SignUpUseCase
import com.balan.androidquestionsapp.domain.usecase.validate.MapValidationSignUpResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: Provider<SignUpUseCase>,
    private val mapValidationSignUpResultUseCase: Provider<MapValidationSignUpResultUseCase>,
) : ViewModel() {

    private val _state: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignUpEvent>()

    val event = _event.asSharedFlow()


    init {
        observeFieldsNotEmptyState()
    }

    private fun observeFieldsNotEmptyState() {
        viewModelScope.launch {
            state
                .map { fieldsNotEmpty(it.email, it.password, it.secondaryPassword, it.name) }
                .distinctUntilChanged()
                .collect { fieldsIsNotEmpty ->
                    _state.update {
                        it.copy(fieldsIsNotEmpty = fieldsIsNotEmpty)
                    }
                }
        }
    }

    private fun fieldsNotEmpty(
        email: String,
        password: String,
        secondaryPassword: String,
        name: String
    ): Boolean {
        return email.isNotEmpty() && password.isNotEmpty() && secondaryPassword.isNotEmpty() && name.isNotEmpty()
    }

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

    fun setSecondaryPassword(secondaryPassword: String) {
        _state.update {
            it.copy(secondaryPassword = secondaryPassword)
        }
    }

    fun setEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    fun onSignInClick() {
        viewModelScope.launch {
            _event.emit(SignUpEvent.NavigationToSignIn)
        }
    }


    fun onSignUpClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val name = _state.value.name
            val password = _state.value.password
            val secondPassword = _state.value.secondaryPassword
            val email = _state.value.email
            val signUpResult =
                signUpUseCase.get().execute(
                    login = name,
                    password = password,
                    secondPassword = secondPassword,
                    email = email
                )

            val (emailValidation, passwordValidation, loginValidation) = mapValidationSignUpResultUseCase.get()
                .execute(
                    signUpResult
                )

            _state.update {
                it.copy(
                    emailValidation = emailValidation,
                    passwordValidation = passwordValidation,
                    loginValidation = loginValidation,
                )
            }
            if (signUpResult == Validation.VALID) {
                _event.emit(SignUpEvent.NavigationSuccessRegistrationToSignIn)
            }
        }
    }


    fun onClearClick(inputFieldType: InputFieldType) {
        when (inputFieldType) {
            InputFieldType.LOGIN -> _state.update { it.copy(name = "") }
            InputFieldType.PASSWORD -> _state.update {
                it.copy(
                    password = "",
                    secondaryPassword = ""
                )
            }

            InputFieldType.EMAIL -> _state.update { it.copy(email = "") }
        }
    }

    fun isErrorValidation(validation: Validation) =
        validation != Validation.VALID && validation != Validation.DEFAULT
}