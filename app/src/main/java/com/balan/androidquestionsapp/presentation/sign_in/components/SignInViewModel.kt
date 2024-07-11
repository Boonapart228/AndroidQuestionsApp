package com.balan.androidquestionsapp.presentation.sign_in.components

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.InputFieldType
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.usecase.auth.SignInUseCase
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
class SignInViewModel @Inject constructor(
    private val signInUseCase: Provider<SignInUseCase>,
) : ViewModel() {

    private val _state: MutableStateFlow<SignInState> =
        MutableStateFlow(SignInState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignInNavigationEvent>()

    val event = _event.asSharedFlow()

    fun setEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
        isFieldsNotEmpty()
    }

    fun setPassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
        isFieldsNotEmpty()
    }

    fun onSignInClick(context : Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val password = _state.value.password
            val email = _state.value.email
            val signInResult = signInUseCase.get().execute(email = email, password = password)
            _state.update {
                it.copy(
                    validation = if (!signInResult) Validation.INVALID else Validation.VALID
                )
            }
            if (signInResult) {
                withContext(Dispatchers.Main){
                Toast.makeText(context, R.string.successful_authorization, Toast.LENGTH_SHORT)
                    .show()
                _event.emit(SignInNavigationEvent.NavigationToSignIn)
            }
            }
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _event.emit(SignInNavigationEvent.NavigationToSignUp)
        }
    }
    fun onShowPasswordClick() {
        _state.update {
            it.copy(showPassword = !_state.value.showPassword)
        }
    }
    

    private fun isFieldsNotEmpty() {
            _state.update {
                it.copy(
                    isFieldsNotEmpty = _state.value.password.isNotEmpty() && _state.value.email.isNotEmpty()
                )
            }
    }

    fun onClearClick(inputFieldType: InputFieldType) {
        when (inputFieldType) {
            InputFieldType.EMAIL -> _state.update { it.copy(email = "") }
            else -> {}
        }
    }

}