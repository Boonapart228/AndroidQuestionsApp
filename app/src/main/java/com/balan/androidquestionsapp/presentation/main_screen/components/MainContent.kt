package com.balan.androidquestionsapp.presentation.main_screen.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R
import com.balan.androidquestionsapp.domain.models.TestType
import com.balan.androidquestionsapp.ui.theme.LocalDimen

@Composable
fun MainContent(
    state: MainState,
    onLogOutClick: () -> Unit,
    onTestClick: (TestType) -> Unit,
    onTestDoubleClick: (TestType) -> Unit
) {
    BackHandler { return@BackHandler }
    Scaffold(
        topBar = {
            TopBarMain(
                onClick = onLogOutClick,
                text = stringResource(id = R.string.test_selection)
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(LocalDimen.current.arrangementSpaceBy12)
        ) {
            items(state.mainButtons) { testType ->
                MainButton(
                    messageResId = testType.messageResId,
                    onClick = { onTestClick(testType) },
                    onAdminClick = { onTestDoubleClick(testType) }
                )
                Divider(
                    thickness = LocalDimen.current.driverThickness
                )
            }
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainButton(
    messageResId: Int,
    onAdminClick: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(LocalDimen.current.horizontalRowSpace)
            .combinedClickable(onClick = onClick)
            .fillMaxWidth()
            .padding(
                horizontal = LocalDimen.current.horizontalRowPadding
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalDimen.current.horizontalRowSpace)
    ) {
        Text(
            text = stringResource(id = messageResId),
            fontSize = LocalDimen.current.textSize16,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onAdminClick) {
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
        }
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MainContentPreview() {
    MainContent(
        state = MainState(
            mainButtons = listOf(TestType.SENIOR, TestType.JUNIOR)
        ),
        onLogOutClick = {}, onTestClick = { },
    ) { }
}
