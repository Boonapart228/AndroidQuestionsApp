package com.balan.androidquestionsapp.presentation.test_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.Answer
import com.balan.androidquestionsapp.domain.models.QuestionType
import com.balan.androidquestionsapp.domain.usecase.test.GetQuestionsUseCase
import com.balan.androidquestionsapp.domain.usecase.test.UpdateScoreUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetCurrentUserUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetLevelUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.UpdateUserInfoUseCase
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
class TestViewModel @Inject constructor(
    private val getLevelUseCase: Provider<GetLevelUseCase>,
    private val getCurrentUserUseCase: Provider<GetCurrentUserUseCase>,
    private val updateUserInfoUseCase: Provider<UpdateUserInfoUseCase>,
    private val getQuestionsUseCase: Provider<GetQuestionsUseCase>,
    private val updateScoreUseCase: Provider<UpdateScoreUseCase>
) : ViewModel() {

    private val _state: MutableStateFlow<TestState> = MutableStateFlow(TestState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<TestNavigationEvent>()

    val event = _event.asSharedFlow()

    init {
        _state.update {
            it.copy(questions = getQuestionsUseCase.get().execute())
        }
    }

    fun nextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value.answered) {
                val question = getLevelUseCase.get().execute()
                checkAnswer()
                if (_state.value.questionNumber < _state.value.questions.lastIndex) {
                    _state.update {
                        it.copy(
                            questionNumber = it.questionNumber + 1
                        )
                    }
                } else if (_state.value.questionNumber == _state.value.questions.lastIndex) {
                    val currentUser = getCurrentUserUseCase.get().execute()
                    currentUser?.let {
                        val user = updateScoreUseCase.get().execute(
                            score = _state.value.score,
                            user = currentUser,
                            questionLevel = question
                        )
                        updateUserInfoUseCase.get().execute(user)
                        navigateResultScreen()
                    }
                }
            }
        }
    }

    private fun navigateResultScreen() {
        viewModelScope.launch {
            _event.emit(TestNavigationEvent.NavigationToResult)
        }
    }

    fun previousQuestion() {
        if (_state.value.questionNumber != 0) {
            _state.update {
                it.copy(
                    questionNumber = 0,
                    score = if (it.score != 0) it.score - 1 else 0,
                    answered = false
                )
            }
        }
    }

    fun setAnswer(text: String) {
        _state.update {
            it.copy(writtenAnswer = text)
        }
        setAnswered()
    }

    private fun setAnswered() {
        _state.update {
            it.copy(
                answered = true
            )
        }
    }

    fun onAnswerRadioButtonClick(answer: Answer) {
        _state.update {
            it.copy(
                selectedRadioAnswer = answer
            )
        }
        setAnswered()
    }

    private fun checkRadioButtonAnswer() {
        val copySelectedRadioAnswer = _state.value.selectedRadioAnswer?.copy(isTrue = false)
        if (_state.value.selectedRadioAnswer?.isTrue == true) {
            _state.update {
                it.copy(
                    score = it.score + 1,
                    selectedRadioAnswer = copySelectedRadioAnswer,
                )
            }
        }
    }

    private fun checkCheckBoxAnswer() {
        val copySelectedCheckAnswer: List<Answer> = emptyList()
        if (_state.value.selectedCheckAnswer.size > 1 && _state.value.selectedCheckAnswer.all { it.isTrue }) {
            _state.update {
                it.copy(
                    score = it.score + 1,
                    selectedCheckAnswer = copySelectedCheckAnswer,
                )
            }
        }
    }

    private fun checkTextFieldAnswer() {
        val questionNumber = _state.value.questionNumber
        if (_state.value.writtenAnswer.lowercase() == _state.value.questions[questionNumber].answers[0].title.lowercase()) {
            _state.update {
                it.copy(
                    score = it.score + 1,
                    writtenAnswer = "",
                )
            }
        }
    }

    fun onAnswerCheckButtonClick(answer: Answer) {
        val answers = _state.value.selectedCheckAnswer.toMutableList()
        if (answers.contains(answer)) {
            answers.remove(answer)
        } else {
            answers.add(answer)
        }
        _state.update {
            it.copy(
                selectedCheckAnswer = answers,
            )
        }
        setAnswered()
    }

    private fun checkAnswer() {
        val index = _state.value.questionNumber
        _state.update {
            it.copy(
                answered = false
            )
        }
        when (_state.value.questions[index].type) {
            QuestionType.RADIO_BUTTON.type -> {
                checkRadioButtonAnswer()
            }

            QuestionType.CHECK_BOX.type -> {
                checkCheckBoxAnswer()
            }

            QuestionType.TEXT_FIELD.type -> {
                checkTextFieldAnswer()
            }
        }
    }


    fun onMainClick() {
        viewModelScope.launch {
            _event.emit(TestNavigationEvent.NavigationToMenu)
        }
    }

}