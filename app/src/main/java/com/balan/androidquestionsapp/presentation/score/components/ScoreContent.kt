package com.balan.androidquestionsapp.presentation.score.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.domain.models.QuestionLevel
import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.presentation.score.components.contents.BottomBarScore
import com.balan.androidquestionsapp.presentation.topbar.TopBar
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun ScoreContent(
    onMainClick: () -> Unit,
    viewModel: ScoreViewModel,
    modifier: Modifier = Modifier,
    onSortByDecreasingScoreClick: () -> Unit,
    onSortByIncreasingScoreClick: () -> Unit,
    onSortByNameClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val users = state.users
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopBar(onClick = onMainClick, imageVector = Icons.Filled.Home)
        },
        bottomBar = {
            BottomBarScore(
                onSortByNameClick = onSortByNameClick,
                onSortByIncreasingScoreClick = onSortByDecreasingScoreClick,
                onSortByDecreasingScoreClick = onSortByIncreasingScoreClick
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(users) { user ->
                ScoreItem(
                    user = user,
                    state = state,
                    onDeleteUserScoreClick = { viewModel.onDeleteScoreClick(user) }
                )
            }
        }
    }
}

@Composable
fun ScoreItem(
    user: User,
    state: ScoreState,
    onDeleteUserScoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val score = when (state.level) {
        QuestionLevel.JUNIOR -> user.question.junior
        QuestionLevel.MIDDLE -> user.question.middle
        QuestionLevel.SENIOR -> user.question.senior
        QuestionLevel.DEFAULT -> 0
    }

    val testPassed = score != null && score >= 7

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(LocalDimen.current.spacerPaddingAll8)
            .clip(RoundedCornerShape(LocalDimen.current.scoreCardClip))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = user.name,
                    modifier = Modifier.padding(horizontal = LocalDimen.current.scoreTextHorizontalPadding)
                )
                Spacer(modifier = Modifier.height(LocalDimen.current.spacerHeight4))
                Text(
                    text = user.email,
                    modifier = Modifier.padding(horizontal = LocalDimen.current.scoreTextHorizontalPadding)
                )
            }
            Text(
                text = "Результат: $score",
                modifier = Modifier.padding(horizontal = LocalDimen.current.scoreTextHorizontalPadding)
            )
            Spacer(modifier = Modifier.width(LocalDimen.current.spacerWidth8))
            Button(
                onClick = {},
                shape = CircleShape,
                modifier = Modifier.size(LocalDimen.current.buttonSize),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (testPassed) Color.Green else Color.Red
                )
            ) {}
            Spacer(modifier = Modifier.width(LocalDimen.current.spacerWidth8))
            IconButton(
                onClick = onDeleteUserScoreClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ScoreContentPreview() {
    ScoreItem(
        user = User(
            name = "Calli",
            password = "Keri",
            email = "Nefertiti",
            question = QuestionsScore(
                junior = 8,
                middle = 3,
                senior = 7
            )
        ),
        state = ScoreState(
            level = QuestionLevel.JUNIOR,
            users = listOf()
        ),
        onDeleteUserScoreClick = {},
    )
}