package com.balan.androidquestionsapp.presentation.admin.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.InputFieldType
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.usecase.auth.AuthenticateAdminUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val authenticateAdminUseCase: Provider<AuthenticateAdminUseCase>
) : ViewModel() {
    private val _state = MutableStateFlow(AdminState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<AdminNavigationEvent>()

    val event = _event.asSharedFlow()
    fun setPassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
        isFieldsNotEmpty()
    }

    fun onScoreClick() {
        viewModelScope.launch {
            val adminAccess = authenticateAdminUseCase.get().execute(_state.value.password)
            if (adminAccess) {
                _event.emit(AdminNavigationEvent.NavigationToScore)
            } else {
                _state.update { it.copy(passwordValidation = Validation.INVALID_ADMIN_PASSWORD) }
            }
        }
    }

    private fun isFieldsNotEmpty() {
        _state.update {
            it.copy(
                isFieldsNotEmpty = _state.value.password.isNotEmpty()
            )
        }
    }

    fun onMainClick() {
        viewModelScope.launch {
            _event.emit(AdminNavigationEvent.NavigationToMenu)
        }
    }

    fun isErrorValidation(validation: Validation) =
        validation != Validation.VALID && validation != Validation.DEFAULT

    fun onClearClick(inputFieldType: InputFieldType) {
        when (inputFieldType) {
            InputFieldType.PASSWORD -> _state.update { it.copy(password = "") }
            else -> {}
        }
    }
}