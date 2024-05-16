package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.TestType
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
    private fun testJunior() {
        viewModelScope.launch {
            userSession.questionLevel(QuestionLevel.JUNIOR)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    private fun testMiddle() {
        viewModelScope.launch {
            userSession.questionLevel(QuestionLevel.MIDDLE)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    private fun testSenior() {
        viewModelScope.launch {
            userSession.questionLevel(QuestionLevel.SENIOR)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    private fun adminJunior() {
        viewModelScope.launch {
            userSession.questionLevel(QuestionLevel.JUNIOR)
            _event.emit(MainNavigationEvent.NavigationToAdmin)
        }
    }

    private fun adminMiddle() {
        viewModelScope.launch {
            userSession.questionLevel(QuestionLevel.MIDDLE)
            _event.emit(MainNavigationEvent.NavigationToAdmin)
        }
    }

    private fun adminSenior() {
        viewModelScope.launch {
            userSession.questionLevel(QuestionLevel.SENIOR)
            _event.emit(MainNavigationEvent.NavigationToAdmin)
        }
    }

    fun onSignInClick() {
        viewModelScope.launch {
            _event.emit(MainNavigationEvent.NavigationToSignIn)
        }
    }


    fun onTestClick(type: TestType) {
        when (type) {
            TestType.JUNIOR -> {
                testJunior()
            }

            TestType.MIDDLE -> {
                testMiddle()
            }

            TestType.SENIOR -> {
                testSenior()
            }
        }
    }

    fun onTestDoubleClick(type: TestType) {
        when (type) {
            TestType.JUNIOR -> {
                adminJunior()
            }

            TestType.MIDDLE -> {
                adminMiddle()
            }

            TestType.SENIOR -> {
                adminSenior()
            }
        }
    }


}


