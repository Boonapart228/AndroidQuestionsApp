package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.TestType
import com.balan.androidquestionsapp.domain.usecase.user_session.SetQuestionLevelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userSetQuestionLevelUseCase: Provider<SetQuestionLevelUseCase>
) : ViewModel() {
    private val _event = MutableSharedFlow<MainNavigationEvent>()

    val event = _event.asSharedFlow()
    private fun testJunior() {
        viewModelScope.launch {
            userSetQuestionLevelUseCase.get().execute(QuestionLevel.JUNIOR)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    private fun testMiddle() {
        viewModelScope.launch {
            userSetQuestionLevelUseCase.get().execute(QuestionLevel.MIDDLE)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    private fun testSenior() {
        viewModelScope.launch {
            userSetQuestionLevelUseCase.get().execute(QuestionLevel.SENIOR)
            _event.emit(MainNavigationEvent.NavigationToTest)
        }
    }

    private fun adminJunior() {
        viewModelScope.launch {
            userSetQuestionLevelUseCase.get().execute(QuestionLevel.JUNIOR)
            _event.emit(MainNavigationEvent.NavigationToAdmin)
        }
    }

    private fun adminMiddle() {
        viewModelScope.launch {
            userSetQuestionLevelUseCase.get().execute(QuestionLevel.MIDDLE)
            _event.emit(MainNavigationEvent.NavigationToAdmin)
        }
    }

    private fun adminSenior() {
        viewModelScope.launch {
            userSetQuestionLevelUseCase.get().execute(QuestionLevel.SENIOR)
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


