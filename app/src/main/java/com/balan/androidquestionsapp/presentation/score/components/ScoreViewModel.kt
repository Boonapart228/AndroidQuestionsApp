package com.balan.androidquestionsapp.presentation.score.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.database.UserLocalSource
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.repository.ScoreRepository
import com.balan.androidquestionsapp.domain.user.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val userSession: UserSession,
    private val userLocalSource: UserLocalSource
) : ViewModel() {
    private val _state = MutableStateFlow(ScoreState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ScoreNavigationEvent>()

    val event = _event.asSharedFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            checkLevel()
            update(userSession.getUsers())
        }
    }

    private fun checkLevel() {
        _state.update {
            it.copy(
                level = userSession.getLevel()
            )
        }
    }

    private fun update(userList: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(users = userList)
            }
        }
    }


    fun onDeleteScoreClick(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            update(scoreRepository.deleteResult(user = user, level = userSession.getLevel()))
        }
    }

    fun sortByIncreasingScore() {
        viewModelScope.launch(Dispatchers.IO) {
            update(scoreRepository.sortByIncreasingScore(_state.value.level))
        }
    }

    fun sortByDecreasingScore() {
        viewModelScope.launch(Dispatchers.IO) {
            update(scoreRepository.sortByDecreasingScore(_state.value.level))
        }
    }

    fun sortByName() {
        viewModelScope.launch(Dispatchers.IO) {
            update(scoreRepository.sortByName())
        }
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