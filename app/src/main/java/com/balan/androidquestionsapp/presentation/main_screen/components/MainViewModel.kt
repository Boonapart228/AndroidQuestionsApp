package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _event = MutableSharedFlow<MainNavigationEvent>()

    val event = _event.asSharedFlow()
    fun onTestJuniorClick() {
        mainRepository.setJuniorQuestion()
        viewModelScope.launch {
            _event.emit(MainNavigationEvent.NavigationTest)
        }
    }

    fun onTestMiddleClick() {
        mainRepository.setMiddleQuestion()
        viewModelScope.launch {
            _event.emit(MainNavigationEvent.NavigationTest)
        }
    }

    fun onTestSeniorClick() {
        mainRepository.setSeniorQuestion()
        viewModelScope.launch {
            _event.emit(MainNavigationEvent.NavigationTest)
        }
    }

    fun onSignInClick() {
        viewModelScope.launch {
            _event.emit(MainNavigationEvent.NavigationSignIn)
        }
    }


}


