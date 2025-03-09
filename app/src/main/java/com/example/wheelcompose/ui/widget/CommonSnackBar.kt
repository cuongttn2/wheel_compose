package com.example.wheelcompose.ui.widget

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun CommonSnackBar(
    snackbarHostState: SnackbarHostState,
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration = SnackbarDuration.Short,
    onAction: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {
    LaunchedEffect(message, actionLabel) {
        val result = snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = duration
        )
        when (result) {
            SnackbarResult.ActionPerformed -> onAction?.invoke()
            SnackbarResult.Dismissed -> onDismiss?.invoke()
        }
    }
}