package com.qsd.wheelcompose.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavBackIcon(onBack: () -> Unit) {
    Icon(
        Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "back",
        modifier = Modifier
            .size(24.dp)
            .clickable { onBack.invoke() })
}