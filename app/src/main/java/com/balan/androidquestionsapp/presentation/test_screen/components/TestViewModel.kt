package com.balan.androidquestionsapp.presentation.test_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.Answer
import com.balan.androidquestionsapp.domain.models.QuestionType
import com.balan.androidquestionsapp.domain.repository.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testRepository: TestRepository
) : ViewModel() {

    private val _state: MutableStateFlow<TestState> = MutableStateFlow(TestState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<TestNavigationEvent>()

    val event = _event.asSharedFlow()

    fun indexPlus() {
        checkAnswer()
        if (_state.value.index < _state.value.questions.lastIndex) {
            _state.update {
                it.copy(
                    index = it.index + 1
                )
            }
        } else if (_state.value.index == _state.value.questions.lastIndex) {
            testRepository.updateScore(_state.value.score)
            navigateResultScreen()
        }
    }


    private fun navigateResultScreen() {
        viewModelScope.launch {
            _event.emit(TestNavigationEvent.NavigationResult)
        }
    }

    fun indexMinus() {
        if (_state.value.index != 0) {
            _state.update {
                it.copy(
                    index = it.index - 1,
                    score = if (it.score != 0) it.score - 1 else it.score
                )
            }
        }
    }

    fun setAnswer(text: String) {
        _state.update {
            it.copy(writtenAnswer = text)
        }
    }


    fun onAnswerRadioButtonClick(answer: Answer) {
        _state.update {
            it.copy(
                selectedRadioAnswer = answer
            )
        }
    }

    private fun checkRadioButtonAnswer() {
        val copySelectedRadioAnswer = _state.value.selectedRadioAnswer?.copy(isTrue = false)
        if (_state.value.selectedRadioAnswer?.isTrue == true) {
            _state.update {
                it.copy(
                    score = it.score + 1,
                    selectedRadioAnswer = copySelectedRadioAnswer
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
                    selectedCheckAnswer = copySelectedCheckAnswer
                )
            }
        }
    }

    private fun checkTextFieldAnswer() {
        val index = _state.value.index
        if (_state.value.writtenAnswer.lowercase() == _state.value.questions[index].answers[0].title.lowercase()) {
            _state.update {
                it.copy(
                    score = it.score + 1,
                    writtenAnswer = ""
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
    }

    private fun checkAnswer() {
        val index = _state.value.index
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
            _event.emit(TestNavigationEvent.NavigationMenu)
        }
    }

    init {
        _state.update {
            it.copy(questions = testRepository.getQuestions())
        }
    }
}