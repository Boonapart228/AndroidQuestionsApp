package com.balan.androidquestionsapp.presentation.score.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ScoreRepository
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
class ScoreViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository,
    private val userSession: UserSession
) : ViewModel() {
    private val _state = MutableStateFlow(ScoreState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ScoreNavigationEvent>()

    val event = _event.asSharedFlow()


    init {
        checkLevel()
        update()
    }

    private fun checkLevel() {
        _state.update {
            it.copy(
                level = userSession.getLevel()
            )
        }
    }

    private fun update() {
        _state.update {
            it.copy(users = userSession.getUsers().toMutableList())
        }
    }


    fun onDeleteScoreClick(user: User) {
        viewModelScope.launch {
            scoreRepository.deleteResult(user = user, level = userSession.getLevel())
            update()
        }
    }

    fun sortByIncreasingScore() {
        scoreRepository.sortByIncreasingScore(_state.value.level)
        update()
    }

    fun sortByDecreasingScore() {
        scoreRepository.sortByDecreasingScore(_state.value.level)
        update()
    }

    fun sortByName() {
        scoreRepository.sortByName()
        update()
    }

    fun onMainClick() {
        viewModelScope.launch {
            _event.emit(ScoreNavigationEvent.NavigationToMain)
        }
    }

    fun getScore(user: User): Int? {
        return when (userSession.getLevel()) {
            QuestionLevel.JUNIOR -> user.question.junior
            QuestionLevel.MIDDLE -> user.question.middle
            QuestionLevel.SENIOR -> user.question.senior
            QuestionLevel.DEFAULT -> 0
        }
    }

    fun isTestPassed(score: Int?) = if (score == null) false else score >= 7


}