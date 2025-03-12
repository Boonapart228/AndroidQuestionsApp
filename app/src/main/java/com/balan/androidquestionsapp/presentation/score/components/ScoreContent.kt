package com.balan.androidquestionsapp.presentation.score.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.DialogAction
import com.balan.androidquestionsapp.domain.models.QuestionsScore
import com.balan.androidquestionsapp.domain.models.SortOption
import com.balan.androidquestionsapp.domain.models.User
import com.balan.androidquestionsapp.presentation.score.components.contents.ShimmerLoadingContent
import com.balan.androidquestionsapp.presentation.score.components.contents.TopBarScore
import com.balan.androidquestionsapp.ui.theme.LocalDimen
import com.balan.androidquestionsapp.ui.theme.LocalProperty

@Composable
fun ScoreContent(
    onMainClick: () -> Unit,
    viewModel: ScoreViewModel,
    onSortClick: (SortOption) -> Unit,
    onActiveToggleMenuClick: () -> Unit,
    onConfirmationClick: (DialogAction) -> Unit,
    onActiveSortOptionClick: (SortOption) -> Unit,
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
                onToggleMenuClick = onActiveToggleMenuClick,
                onActiveSortOptionClick = onActiveSortOptionClick
            )
        },
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(users) { user ->
                ScoreItem(
                    user = user,
                    isLoading = state.isLoading,
                    onDeleteUserScoreClick = { viewModel.onDeleteScoreClick(user) },
                    score = viewModel.getScore(user),
                    scoreColor = viewModel.getColorByScore(viewModel.getScore(user))
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
    isLoading: Boolean,
    onDeleteUserScoreClick: () -> Unit,
    score: Int?,
    scoreColor: Color,
    modifier: Modifier = Modifier
) {

    if (!isLoading) {
        ShimmerLoadingContent()
    } else {
        Card(
            elevation = CardDefaults.cardElevation(LocalDimen.current.cardElevation),
            shape = RoundedCornerShape(LocalDimen.current.cardShape),
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = LocalDimen.current.cardHorizontalPadding,
                    vertical = LocalDimen.current.cardVerticalPadding
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalDimen.current.rowPadding),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(LocalDimen.current.verticalSpaced),
                    modifier = Modifier.weight(LocalProperty.current.largeContentWeight)
                ) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = LocalDimen.current.textHorizontalPadding)
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = LocalDimen.current.textHorizontalPadding),
                    )
                }

                Text(
                    text = "${stringResource(R.string.result_score)} $score",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = scoreColor,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(LocalProperty.current.largeContentWeight),
                )

                IconButton(
                    onClick = onDeleteUserScoreClick,
                    modifier = Modifier
                        .size(LocalDimen.current.iconSize24)
                        .align(Alignment.CenterVertically)
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
        isLoading = true,
        score = 0,
        scoreColor = Color.Red,
    )
}