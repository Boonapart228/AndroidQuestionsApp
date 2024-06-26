package com.balan.androidquestionsapp.presentation.sign_in.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.Validation
import com.balan.androidquestionsapp.domain.usecase.auth.SignInUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.SetUserUseCase
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
class SignInViewModel @Inject constructor(
    private val signInUseCase: Provider<SignInUseCase>,
    private val setUserUseCase: Provider<SetUserUseCase>,
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
            _state.update {
                it.copy(
                    validation = if (signInResult == null) Validation.INVALID else Validation.VALID
                )
            }
            if (signInResult != null) {
                setUserUseCase.get().execute(signInResult)
                _event.emit(SignInNavigationEvent.NavigationToSignIn)
            }
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _event.emit(SignInNavigationEvent.NavigationToSignUp)
        }
    }

    fun isFieldsNotEmpty() = !(_state.value.email.isEmpty() || _state.value.password.isEmpty())

    fun onShowPasswordClick() {
        _state.update {
            it.copy(showPassword = !_state.value.showPassword)
        }
    }

}