package com.balan.androidquestionsapp.presentation.sign_in.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.DialogAction
import com.balan.androidquestionsapp.domain.models.InputFieldType
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.usecase.auth.SignInUseCase
import com.balan.androidquestionsapp.domain.usecase.validate.MapValidationSignInResultUseCase
import com.balan.androidquestionsapp.domain.usecase.validate.ValidateSignInUseCase
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
class SignInViewModel @Inject constructor(
    private val signInUseCase: Provider<SignInUseCase>,
    private val mapValidationSignInResultUseCase: Provider<MapValidationSignInResultUseCase>,
    private val validateSignInUseCase: Provider<ValidateSignInUseCase>,
) : ViewModel() {

    private val _state: MutableStateFlow<SignInState> =
        MutableStateFlow(SignInState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignInEvent>()

    val event = _event.asSharedFlow()


    init {
        observeFieldsNotEmptyState()
    }

    private fun observeFieldsNotEmptyState() {
        viewModelScope.launch {
            state
                .map { fieldsNotEmpty(it.email, it.password) }
                .distinctUntilChanged()
                .collect { fieldsIsNotEmpty ->
                    _state.update {
                        it.copy(fieldsIsNotEmpty = fieldsIsNotEmpty)
                    }
                }
        }
    }


    private fun fieldsNotEmpty(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    fun setEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    fun setPassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun onSignInClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val password = _state.value.password
            val email = _state.value.email
            val signInResult = signInUseCase.get().execute(email = email, password = password)
            val signInValidationResult =
                validateSignInUseCase.get().execute(email = email, password = password)
            val (emailValidation, passwordValidation) = mapValidationSignInResultUseCase.get()
                .execute(
                    signInValidationResult
                )
            _state.update {
                it.copy(
                    validationEmail = emailValidation,
                    validationPassword = passwordValidation
                )
            }
            if (signInResult) {
                _event.emit(SignInEvent.NavigationToMain)
            }
        }
    }


    fun onSignUpClick() {
        viewModelScope.launch {
            _event.emit(SignInEvent.NavigationToSignUp)
        }
    }

    fun onShowPasswordClick() {
        _state.update {
            it.copy(showPassword = !_state.value.showPassword)
        }
    }

    fun onClearClick(inputFieldType: InputFieldType) {
        when (inputFieldType) {
            InputFieldType.EMAIL -> _state.update { it.copy(email = "") }
            else -> return
        }
    }

    fun isErrorValidation(validation: Validation) =
        validation != Validation.VALID && validation != Validation.DEFAULT

    fun onExitAppClick() {
        _state.update {
            it.copy(isExitDialogVisible = !_state.value.isExitDialogVisible)

        }
    }

    private fun toggleDialogAlert() {
        _state.update {
            it.copy(isExitDialogVisible = !_state.value.isExitDialogVisible)
        }
    }

    fun handleDialogAction(dialogAction: DialogAction) {
        if (dialogAction == DialogAction.CONFIRM) {
            viewModelScope.launch {
                _event.emit(SignInEvent.ExitApp)
            }
        }
        toggleDialogAlert()
    }
}
