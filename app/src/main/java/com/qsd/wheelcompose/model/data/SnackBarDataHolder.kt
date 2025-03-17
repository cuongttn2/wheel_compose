package com.qsd.wheelcompose.model.data

import androidx.compose.material3.SnackbarDuration

data class SnackBarDataHolder(
    val message: String,
    val actionLabel: String? = null,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val onAction: (() -> Unit)? = null
)