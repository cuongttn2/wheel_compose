package com.example.wheelcompose.ui.wheel

import WheelSpinner
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.wheelcompose.ui.widget.FireworkAnimation
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun WheelScreen(
    segments: List<String>,
    durationMillis: Int = 3000,  // thời gian quay có thể điều chỉnh (mili giây)
    onResult: (String) -> Unit = {} // Callback trả về tên phần được chọn
) {
    var showFireworks by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val sweepAngle = 360f / segments.size

    Box(modifier = Modifier.fillMaxSize()) {
        // Nội dung chính được đặt trong một Column
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Center
        ) {
            WheelSpinner(
                segments = segments,
                modifier = Modifier
                    .size(300.dp)
                    .rotate(rotation.value)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                coroutineScope.launch {
                    val fullSpins = (11..13).random() // quay từ 11 đến 13 vòng
                    val extraRotation = Random.nextFloat() * 360f
                    val targetRotation = rotation.value + 360f * fullSpins + extraRotation
                    rotation.animateTo(
                        targetRotation,
                        animationSpec = tween(durationMillis = durationMillis, easing = FastOutSlowInEasing)
                    )
                    val finalRotation = rotation.value % 360
                    val effectiveAngle = (360 - finalRotation) % 360
                    val selectedIndex = (effectiveAngle / sweepAngle).toInt() % segments.size
                    onResult(segments[selectedIndex])
                    showFireworks = true
                }
            }) {
                Text("Spin")
            }
        }

        FireworkAnimation(
            isVisible = showFireworks,
            modifier = Modifier.fillMaxSize(),
            onAnimationFinished = { showFireworks = false }
        )
    }
}