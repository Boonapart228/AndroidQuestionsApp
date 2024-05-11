package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.user.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {
    private val _event = MutableSharedFlow<MainNavigationEvent>()

    val event = _event.asSharedFlow()
    fun onTestJuniorClick() {
        viewModelScope.launch {
            userSession.setLevel(QuestionLevel.JUNIOR)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    fun onTestMiddleClick() {
        viewModelScope.launch {
            userSession.setLevel(QuestionLevel.MIDDLE)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    fun onTestSeniorClick() {
        viewModelScope.launch {
            userSession.setLevel(QuestionLevel.SENIOR)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    fun onAdminJuniorDoubleClick() {
        viewModelScope.launch {
            userSession.setLevel(QuestionLevel.JUNIOR)
            _event.emit(MainNavigationEvent.NavigationToAdmin)
        }
    }

    fun onAdminMiddleDoubleClick() {
        viewModelScope.launch {
            userSession.setLevel(QuestionLevel.MIDDLE)
            _event.emit(MainNavigationEvent.NavigationToAdmin)
        }
    }

    fun onAdminDoubleSeniorClick() {
        viewModelScope.launch {
            userSession.setLevel(QuestionLevel.SENIOR)
            _event.emit(MainNavigationEvent.NavigationToAdmin)
        }
    }

    fun onSignInClick() {
        viewModelScope.launch {
            _event.emit(MainNavigationEvent.NavigationToSignIn)
        }
    }


}


