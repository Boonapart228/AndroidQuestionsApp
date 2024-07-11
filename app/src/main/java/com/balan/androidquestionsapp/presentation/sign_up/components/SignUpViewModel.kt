package com.balan.androidquestionsapp.presentation.sign_up.components

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.InputFieldType
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
import kotlinx.coroutines.withContext
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
        isFieldsNotEmpty()
    }

    fun setPassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
        isFieldsNotEmpty()
    }

    fun setEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
        isFieldsNotEmpty()
    }

    fun onSignInClick() {
        viewModelScope.launch {
            _event.emit(SignUpNavigationEvent.NavigationToSignIn)
        }
    }


    fun onSignUpClick(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val name = _state.value.name
            val password = _state.value.password
            val email = _state.value.email
            val signUpResult =
                signUpUseCase.get().execute(login = name, password = password, email = email)

            val (emailValidation, passwordValidation, loginValidation) = mapValidationResult(
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
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, R.string.successful_registration, Toast.LENGTH_SHORT)
                        .show()
                    _event.emit(SignUpNavigationEvent.NavigationToSignIn)
                }
            }
        }
    }


    private fun isFieldsNotEmpty() {
        _state.update {
            it.copy(
                isFieldsNotEmpty = _state.value.password.isNotEmpty() && _state.value.email.isNotEmpty() && _state.value.name.isNotEmpty()
            )
        }
    }

    private fun mapValidationResult(result: Validation): Triple<Validation, Validation, Validation> {
        val emailValidation = when (result) {
            Validation.INVALID_EMAIL -> Validation.INVALID_EMAIL
            Validation.EMAIL_ALREADY_EXIST -> Validation.EMAIL_ALREADY_EXIST
            else -> Validation.VALID
        }

        val passwordValidation = when (result) {
            Validation.INVALID_ADMIN_PASSWORD -> Validation.INVALID_ADMIN_PASSWORD
            Validation.TOO_SHORT -> Validation.TOO_SHORT
            Validation.NO_UPPERCASE_LETTER -> Validation.NO_UPPERCASE_LETTER
            Validation.NO_LOWERCASE_LETTER -> Validation.NO_LOWERCASE_LETTER
            Validation.NO_DIGIT -> Validation.NO_DIGIT
            Validation.NO_SPECIAL_CHARACTER -> Validation.NO_SPECIAL_CHARACTER
            else -> Validation.VALID
        }

        val loginValidation = when (result) {
            Validation.EMPTY_LOGIN -> Validation.EMPTY_LOGIN
            Validation.TOO_SHORT_LOGIN -> Validation.TOO_SHORT_LOGIN
            Validation.INVALID_CHARACTERS_IN_LOGIN -> Validation.INVALID_CHARACTERS_IN_LOGIN
            else -> Validation.VALID
        }
        return Triple(emailValidation, passwordValidation, loginValidation)
    }

    fun onClearClick(inputFieldType: InputFieldType) {
        when (inputFieldType) {
            InputFieldType.LOGIN -> _state.update { it.copy(name = "") }
            InputFieldType.PASSWORD -> _state.update { it.copy(password = "") }
            InputFieldType.EMAIL -> _state.update { it.copy(email = "") }
        }
    }

    fun isErrorValidation(validation: Validation) =
        validation != Validation.VALID && validation != Validation.DEFAULT
}