package com.balan.androidquestionsapp.presentation.sign_in.contents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.balan.androidquestionsapp.R

@Composable
fun ExitAppConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Filled.Info, contentDescription = null)
        },
        title = {
            Text(text = stringResource(id = R.string.alert_exit_app))
        },
        text = {
            Text(text = stringResource(id = R.string.alert_exit_app_message))
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

@Preview
@Composable
fun ExitAppConfirmationDialogPreview(){
    ExitAppConfirmationDialog(onDismissRequest = {  }) {
        
    }
}