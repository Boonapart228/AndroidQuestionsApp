package com.balan.androidquestionsapp.presentation.score.components

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.DialogAction
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.SortOption
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.domain.usecase.score.DeleteResultUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetLevelUseCase
import com.balan.androidquestionsapp.domain.usecase.user_source.GetAllUserUseCase
import com.balan.androidquestionsapp.domain.usecase.user_source.GetUsersSortedByOptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
    private val getAllUserUseCase: Provider<GetAllUserUseCase>,
    private val getLevelUseCase: Provider<GetLevelUseCase>,
    private val getUsersSortedByOptionUseCase: Provider<GetUsersSortedByOptionUseCase>

) : ViewModel() {
    companion object {
        const val PASSING_SCORE = 7
        val TEST_PASSED_GREEN: Color = Color(0xBF00FF00)
        val TEST_FAILED_RED: Color = Color(0xBEFF0000)
    }

    private val _state = MutableStateFlow(ScoreState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ScoreNavigationEvent>()

    val event = _event.asSharedFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            checkLevel()
            update(getAllUserUseCase.get().execute())
        }
        setSortOption(SortOption.INCREASING)
        triggerLoadingEffect()
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
            it.copy(
                users = userList
            )
        }
    }


    fun onDeleteScoreClick(user: User) {
        _state.update {
            it.copy(user = user)
        }
        toggleDialogAlert()
    }

    fun setSortOption(sortOption: SortOption) {
        viewModelScope.launch(Dispatchers.IO) {
            update(getUsersSortedByOptionUseCase.get().execute(sortOption))
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

    fun getColorByScore(score: Int?): Color {
        return if (score != null && score >= PASSING_SCORE) TEST_PASSED_GREEN
        else TEST_FAILED_RED
    }

    private fun toggleDialogAlert() {
        _state.update {
            it.copy(isDeleteDialogVisible = !_state.value.isDeleteDialogVisible)
        }
    }

    fun handleDialogAction(dialogAction: DialogAction) {
        val currentUser = _state.value.user
        if (dialogAction == DialogAction.CONFIRM)
            if (currentUser != null) {
                viewModelScope.launch {
                    update(
                        deleteResultUseCase.get()
                            .execute(
                                user = currentUser
                            )
                    )
                }
            }
        toggleDialogAlert()
    }

    private fun triggerLoadingEffect() {
        viewModelScope.launch {
            delay(2000)
            _state.update {
                it.copy(isLoading = true)
            }
        }
    }

    fun onActiveSortOptionClick(sortOption: SortOption) {
        _state.update { it.copy(sortOption = sortOption) }
    }
}