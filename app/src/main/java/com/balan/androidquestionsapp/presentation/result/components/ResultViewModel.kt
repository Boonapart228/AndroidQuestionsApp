package com.balan.androidquestionsapp.presentation.result.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.repository.ResultRepository
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
    private val resultRepository: ResultRepository
) : ViewModel() {

    private val _state: MutableStateFlow<ResultState> = MutableStateFlow(ResultState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ResultNavigationEvent>()

    val event = _event.asSharedFlow()

    init {
        getQuestionSize()
        getQuestionScore()
    }

    private fun getQuestionSize() {
        _state.update {
            it.copy(
                size = resultRepository.getQuestionSize()
            )
        }
    }

    private fun getQuestionScore() {
        _state.update {
            it.copy(
                score = resultRepository.getQuestionScore()
            )
        }
    }


    fun onMainClick() {
        viewModelScope.launch {
            _event.emit(ResultNavigationEvent.NavigationMenu)
        }
    }
}