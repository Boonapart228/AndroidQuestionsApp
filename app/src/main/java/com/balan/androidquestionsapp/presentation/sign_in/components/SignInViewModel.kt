package com.balan.androidquestionsapp.presentation.sign_in.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.repository.AuthRepository
import com.balan.androidquestionsapp.domain.models.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state: MutableStateFlow<SignInState> =
        MutableStateFlow(SignInState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignInNavigationEvent>()

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

    fun onSignInClick() {
        viewModelScope.launch {
            val password = _state.value.password
            val name = _state.value.name
            val signInResult = authRepository.signIn(login = name, password = password)

            _state.update {
                it.copy(
                    validation = if (!signInResult) Validation.INVALID else Validation.VALID
                )
            }
            if (signInResult) _event.emit(SignInNavigationEvent.NavigationToSignIn)
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _event.emit(SignInNavigationEvent.NavigationToSignUp)
        }
    }
}