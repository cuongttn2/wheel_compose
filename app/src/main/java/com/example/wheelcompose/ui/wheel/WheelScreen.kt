package com.example.wheelcompose.ui.wheel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wheelcompose.ui.widget.WheelSpinner

@Composable
fun WheelScreen() {
    // Danh sách các phần của wheel
    val segments = listOf("Phần 1", "Phần 2", "Phần 3", "Phần 4", "Phần 5", "Phần 6")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Vẽ wheel với kích thước 300dp x 300dp
        WheelSpinner(segments = segments, modifier = Modifier.size(300.dp))
    }
}