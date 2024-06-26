package com.balan.androidquestionsapp.presentation.score.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.SortDirection
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.usecase.score.DeleteResultUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetLevelUseCase
import com.balan.androidquestionsapp.domain.usecase.user_source.GetAllUseCase
import com.balan.androidquestionsapp.domain.usecase.user_source.SortByDirectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val deleteResultUseCase: Provider<DeleteResultUseCase>,
    private val getAllUseCase: Provider<GetAllUseCase>,
    private val getLevelUseCase: Provider<GetLevelUseCase>,
    private val sortByDirectionUseCase: Provider<SortByDirectionUseCase>

) : ViewModel() {
    companion object {
        const val PASSING_SCORE = 7
    }

    private val _state = MutableStateFlow(ScoreState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ScoreNavigationEvent>()

    val event = _event.asSharedFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            checkLevel()
            update(getAllUseCase.get().execute())
        }
    }

    private fun checkLevel() {
        _state.update {
            it.copy(
                level = getLevelUseCase.get().execute()
            )
        }
    }

    private fun update(userList: List<User>) {
        _state.update {
            it.copy(users = userList)
        }
    }


    fun onDeleteScoreClick(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            update(
                deleteResultUseCase.get()
                    .execute(user = user, level = getLevelUseCase.get().execute())
            )
        }
    }

    fun sort(sortDirection: SortDirection) {

        viewModelScope.launch(Dispatchers.IO) {
            update(sortByDirectionUseCase.get().execute(sortDirection))
        }

    }

    fun onToggleMenuClick() {
        _state.update {
            it.copy(menuExpanded = !it.menuExpanded)
        }
    }

    fun onMainClick() {
        viewModelScope.launch {
            _event.emit(ScoreNavigationEvent.NavigationToMain)
        }
    }

    fun getScore(user: User): Int? {
        return when (getLevelUseCase.get().execute()) {
            QuestionLevel.JUNIOR -> user.question.junior
            QuestionLevel.MIDDLE -> user.question.middle
            QuestionLevel.SENIOR -> user.question.senior
            QuestionLevel.DEFAULT -> 0
        }
    }

    fun isTestPassed(score: Int?) = score != null && score >= PASSING_SCORE

}