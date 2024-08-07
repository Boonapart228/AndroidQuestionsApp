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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.DialogAction
import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.SortDirections
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.presentation.score.components.contents.TopBarScore
import com.balan.androidquestionsapp.ui.theme.LocalColors
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun ScoreContent(
    onMainClick: () -> Unit,
    viewModel: ScoreViewModel,
    onSortClick: (SortDirections) -> Unit,
    onActiveClick: () -> Unit,
    onConfirmationClick: (DialogAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    val users = state.users
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopBarScore(
                onClick = onMainClick,
                onSelectOptionClick = onSortClick,
                state = state,
                onToggleMenuClick = onActiveClick
            )
        },
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(users) { user ->
                ScoreItem(
                    user = user,
                    onDeleteUserScoreClick = { viewModel.onDeleteScoreClick(user) },
                    getScore = { viewModel.getScore(user) },
                    isTestPassed = viewModel::isTestPassed
                )
            }
        }
        if (state.isDeleteDialogVisible) {
            DeleteUserConfirmationDialog(
                onDismissRequest = { onConfirmationClick(DialogAction.DISMISS) },
                onConfirmation = { onConfirmationClick(DialogAction.CONFIRM) },
            )
        }
    }
}

@Composable
fun ScoreItem(
    user: User,
    onDeleteUserScoreClick: () -> Unit,
    getScore: () -> Int?,
    isTestPassed: (Int?) -> Boolean,
    modifier: Modifier = Modifier
) {
    val score = getScore()

    val testPassed = isTestPassed(score)
    if (score != null) {
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
                    text = "${stringResource(id = R.string.result_score)} $score",
                    modifier = Modifier.padding(horizontal = LocalDimen.current.scoreTextHorizontalPadding)
                )
                Spacer(modifier = Modifier.width(LocalDimen.current.spacerWidth8))
                Button(
                    onClick = {},
                    shape = CircleShape,
                    modifier = Modifier.size(LocalDimen.current.buttonSize),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (testPassed) LocalColors.current.testPassedGreen else LocalColors.current.testFailedRed
                    )
                ) {}
                Spacer(modifier = Modifier.width(LocalDimen.current.spacerWidth8))
                IconButton(
                    onClick = onDeleteUserScoreClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                    )
                }

            }
        }
    }
}

@Composable
fun DeleteUserConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Filled.Info, contentDescription = null)
        },
        title = {
            Text(text = stringResource(id = R.string.alert_delete_score))
        },
        text = {
            Text(text = stringResource(id = R.string.alert_delete_score_message))
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmation

            ) {
                Text(stringResource(id = R.string.action_confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.action_dismiss))
            }
        }
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ScoreContentPreview() {
    ScoreItem(
        user = User(
            id = 0,
            name = "Calli",
            password = "Keri",
            email = "Nefertiti",
            question = QuestionsScore(
                junior = 8,
                middle = 3,
                senior = 7
            )
        ),
        onDeleteUserScoreClick = {},
        getScore = { 5 },
        isTestPassed = { false },
    )
}