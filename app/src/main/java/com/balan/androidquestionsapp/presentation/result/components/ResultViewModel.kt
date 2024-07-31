package com.balan.androidquestionsapp.presentation.result.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.usecase.result.GetQuestionScoreUseCase
import com.balan.androidquestionsapp.domain.usecase.result.SetQuestionSizeUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetCurrentUserUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetLevelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val getLevelUseCase: Provider<GetLevelUseCase>,
    private val getCurrentUserUseCase: Provider<GetCurrentUserUseCase>,
    private val setQuestionSizeUseCase: Provider<SetQuestionSizeUseCase>,
    private val getQuestionScoreUseCase: Provider<GetQuestionScoreUseCase>
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
        val question = getLevelUseCase.get().execute()
        _state.update {
            it.copy(
                questionSize = setQuestionSizeUseCase.get().execute(question)
            )
        }
    }

    private fun setQuestionScore() {
        val question = getLevelUseCase.get().execute()
        val user = getCurrentUserUseCase.get().execute()
        _state.update {
            it.copy(
                score = if (user != null) {
                    getQuestionScoreUseCase.get().execute(user = user, question = question)
                } else 0
            )
        }
    }

    fun getAnimation() = if(_state.value.score >= 7) R.raw.animation_test_success else R.raw.animation_test_failed


    fun onMainClick() {
        viewModelScope.launch {
            _event.emit(ResultNavigationEvent.NavigationToMenu)
        }
    }
}