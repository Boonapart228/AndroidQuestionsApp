package com.balan.androidquestionsapp.presentation.test_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balan.androidquestionsapp.domain.models.Answer
import com.balan.androidquestionsapp.domain.models.QuestionType
import com.balan.androidquestionsapp.domain.usecase.test.GetQuestionTitleUseCase
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
    private val updateScoreUseCase: Provider<UpdateScoreUseCase>,
    private val getQuestionTitleUseCase: Provider<GetQuestionTitleUseCase>
) : ViewModel() {

    private val _state: MutableStateFlow<TestState> = MutableStateFlow(TestState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<TestNavigationEvent>()

    val event = _event.asSharedFlow()

    init {
        _state.update {
            it.copy(
                questions = getQuestionsUseCase.get().execute(),
                title = getQuestionTitleUseCase.get().execute()
            )
        }
    }

    fun nextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value.answered) {
                checkAnswer()
                if (_state.value.questionNumber < _state.value.questions.lastIndex) {
                    _state.update {
                        it.copy(
                            questionNumber = it.questionNumber + 1
                        )
                    }
                } else if (_state.value.questionNumber == _state.value.questions.lastIndex) {
                    finishTest()
                }
            }
        }
    }

    private fun finishTest() {
        val question = getLevelUseCase.get().execute()
        val currentUser = getCurrentUserUseCase.get().execute()
        currentUser?.let {
            val user = updateScoreUseCase.get().execute(
                score = getScore(),
                user = currentUser,
                questionLevel = question
            )
            updateUserInfoUseCase.get().execute(user)
            navigateResultScreen()
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
                    questionNumber = it.questionNumber - 1,
                    score = 0,
                    answered = false,
                    selectedRadioAnswer = null,
                    selectedCheckAnswer = listOf(),
                    writtenAnswer = ""
                )
            }
            removeLastAnswerScore()
        }
    }

    fun setAnswer(text: String) {
        _state.update {
            it.copy(writtenAnswer = text)
        }
        setAnswered()
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
        val selectedRadioAnswer = _state.value.selectedRadioAnswer
        val copySelectedRadioAnswer = selectedRadioAnswer?.copy(isTrue = false)

        _state.update {
            it.copy(
                selectedRadioAnswer = if (selectedRadioAnswer?.isTrue == true) copySelectedRadioAnswer else selectedRadioAnswer,
                point = if (selectedRadioAnswer?.isTrue == true) 1 else 0
            )
        }

        addAnswerScore(_state.value.point)
    }

    private fun setAnswered() {
        _state.update {
            it.copy(
                answered = true
            )
        }
    }

    private fun addAnswerScore(point: Int) {
        val scores = _state.value.answersScores.toMutableList()
        scores.add(point)
        _state.update {
            it.copy(
                answersScores = scores
            )
        }
    }

    private fun getScore() = _state.value.answersScores.sum()

    private fun removeLastAnswerScore() {
        val scoreList = _state.value.answersScores.toMutableList()
        if (scoreList.isNotEmpty()) {
            scoreList.removeLast()
            _state.update {
                it.copy(
                    answersScores = scoreList
                )
            }
        }
    }


    private fun checkCheckBoxAnswer() {
        val selectedCheckAnswer = _state.value.selectedCheckAnswer
        _state.update {
            it.copy(
                point = if (selectedCheckAnswer.isNotEmpty() && selectedCheckAnswer.size > 1 && selectedCheckAnswer.all { answer -> answer.isTrue }) 1 else 0,
                selectedCheckAnswer = if (_state.value.point == 1) emptyList() else selectedCheckAnswer
            )
        }

        addAnswerScore(_state.value.point)
    }


    private fun checkTextFieldAnswer() {
        val questionNumber = _state.value.questionNumber
        val writtenAnswer = _state.value.writtenAnswer.lowercase()
        val correctAnswer = _state.value.questions[questionNumber].answers[0].title.lowercase()

        val isCorrectAnswer = writtenAnswer == correctAnswer
        val point = if (isCorrectAnswer) 1 else 0

        _state.update {
            it.copy(
                writtenAnswer = ""
            )
        }

        addAnswerScore(point)
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
        if (answers.isNotEmpty()) setAnswered() else _state.update { it.copy(answered = false) }
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