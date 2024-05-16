package com.balan.androidquestionsapp.presentation.admin.components

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
class AdminViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AdminState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<AdminNavigationEvent>()

    val event = _event.asSharedFlow()
    fun setPassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun onScoreClick() {
        viewModelScope.launch {
            val adminAccess = authRepository.adminAccess(_state.value.password)
            if (adminAccess) {
                _event.emit(AdminNavigationEvent.NavigationToScore)
            } else {
                _state.update { it.copy(validPassword = Validation.INVALID_ADMIN_PASSWORD) }
            }
        }
    }

    fun onMainClick() {
        viewModelScope.launch {
            _event.emit(AdminNavigationEvent.NavigationToMenu)
        }
    }
}