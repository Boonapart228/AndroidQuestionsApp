package com.balan.androidquestionsapp.presentation.authorization_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.repository.AuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) : ViewModel() {

    private val _state: MutableStateFlow<AuthorizationState> =
        MutableStateFlow(AuthorizationState())

    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<AuthorizationNavigationEvent>()

    val event = _event.asSharedFlow()


    fun setLogin(login: String) {
        _state.update {
            it.copy(login = login)
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
            val login = _state.value.login
            val pass = authorizationRepository.authorization(login = login, password = password)
            _state.update {
                it.copy(
                    access = if (!pass) R.string.no_valid_login else R.string.clear
                )
            }
            if (pass) _event.emit(AuthorizationNavigationEvent.NavigationSingIn)
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _event.emit(AuthorizationNavigationEvent.NavigationSingUp)
        }
    }
}