package com.balan.androidquestionsapp.presentation.result.components

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.balan.androidquestionsapp.domain.user.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val resultRepository: ResultRepository,
    private val userSession: UserSession
) : ViewModel() {

    private val _state: MutableStateFlow<ResultState> = MutableStateFlow(ResultState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ResultNavigationEvent>()

    val event = _event.asSharedFlow()

    init {
        setQuestionSize()
        setQuestionScore()
    }

    private fun setQuestionSize() {
        val question = userSession.getLevel()
        _state.update {
            it.copy(
                questionSize = resultRepository.getQuestionSize(question)
            )
        }
    }

    private fun setQuestionScore() {
        val question = userSession.getLevel()
        _state.update {
            it.copy(
                score = resultRepository.getQuestionScore(question)
            )
        }
    }


    fun onMainClick() {
        viewModelScope.launch {
            _event.emit(ResultNavigationEvent.NavigationToMenu)
        }
    }
}