package com.example.wheelcompose.ui.wheel

import WheelSpinner
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wheelcompose.ui.widget.FireworkAnimation
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun WheelScreen(
    segments: List<String>,
    durationMillis: Int = 5000,  // Thời gian quay (có thể điều chỉnh)
    onResult: (String) -> Unit = {}, // Callback trả về tên phần được chọn
) {
    var showFireworks by remember { mutableStateOf(false) }
    var spinForce by remember { mutableFloatStateOf(0f) }
    val rotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val sweepAngle = 360f / segments.size

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Hiển thị WheelContent ở trên
        WheelContent(
            segments = segments,
            rotation = rotation.value,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Thanh slider nằm ngang bên dưới wheel
        ForceSlider(
            force = spinForce,
            onForceChange = { spinForce = it },
            onForceChangeFinished = {
                if (spinForce != 0f) {
                    coroutineScope.launch {
                        // Sử dụng giá trị spinForce để tính số vòng quay:
                        // Nếu spinForce = 0 thì không spin.
                        val fullSpins = 10 + (spinForce / 100f) * 10
                        val extraRotation = Random.nextFloat() * 360f
                        val targetRotation = rotation.value + 360f * fullSpins + extraRotation
                        rotation.animateTo(
                            targetRotation,
                            animationSpec = tween(
                                durationMillis = durationMillis,
                                easing = FastOutSlowInEasing
                            )
                        )
                        // Tính toán segment được chọn dựa trên góc quay
                        val finalRotation = rotation.value % 360
                        val effectiveAngle = (360 - finalRotation) % 360
                        val selectedIndex = (effectiveAngle / sweepAngle).toInt() % segments.size
                        onResult(segments[selectedIndex])
                        showFireworks = true
                    }
                }
                spinForce = 0f // Reset lại giá trị lực sau khi spin
            }
        )
    }
    // Overlay hiển thị animation firework khi spin kết thúc
    FireworkAnimation(
        isVisible = showFireworks,
        modifier = Modifier.fillMaxSize(),
        onAnimationFinished = { showFireworks = false }
    )
}

@Composable
fun WheelContent(
    segments: List<String>,
    rotation: Float,
    modifier: Modifier = Modifier,
) {
    WheelSpinner(
        segments = segments,
        modifier = modifier.rotate(rotation)
    )
}

@Composable
fun ForceSlider(
    force: Float,
    onForceChange: (Float) -> Unit,
    onForceChangeFinished: () -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(fraction = 0.6f)
            .height(20.dp),
        contentAlignment = Alignment.Center
    ) {
        // Vẽ background gradient cho track
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            drawRoundRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFB3E5FC), // Xanh nhạt
                        Color(0xFF81D4FA), // Xanh nhạt đậm hơn
                        Color(0xFFFFB74D), // Vàng cam
                        Color(0xFFFFA726), // Vàng cam đậm
                        Color(0xFFF44336)  // Đỏ
                    )
                ),
                size = size,
                cornerRadius = CornerRadius(20.dp.toPx(), 20.dp.toPx())
            )
        }
        // Slider với track màu trong suốt, để background gradient hiện ra phía sau
        Slider(
            value = force,
            onValueChange = onForceChange,
            onValueChangeFinished = onForceChangeFinished,
            valueRange = valueRange,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent
            )
        )

        Text(
            text = "$force %",
            fontSize = 16.sp, fontWeight = FontWeight.Bold,
            color = Color.White, fontStyle = FontStyle.Italic
        )

    }
}
