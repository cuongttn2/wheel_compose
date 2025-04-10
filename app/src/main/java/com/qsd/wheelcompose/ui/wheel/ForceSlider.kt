package com.qsd.wheelcompose.ui.wheel

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.ui.theme.forceColors
import com.qsd.wheelcompose.ui.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun ForceSlider(
    force: Float,
    onForceChange: (Float) -> Unit,
    onForceChangeFinished: () -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Vẽ background gradient cho track
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            drawRoundRect(
                brush = Brush.horizontalGradient(colors = forceColors),
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
            text = "${(force * 10).roundToInt() / 10f} %",
            style = typography.titleMedium,
            color = Color.White,
            fontStyle = FontStyle.Italic
        )

    }
}

fun ColumnScope.spinWheel(
    coroutineScope: CoroutineScope,
    segments: List<String>,
    spinForce: Float,
    durationMillis: Int,
    sweepAngle: Float,
    rotation: Animatable<Float, AnimationVector1D>,
    onResetSpinForce: () -> Unit,
    onHandleResult: (String) -> Unit,
) {
    if (segments.size > 1 && spinForce != 0f) {
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
            val selectedIndex =
                (effectiveAngle / sweepAngle).toInt() % segments.size
            onHandleResult(segments[selectedIndex])
        }
    }
    onResetSpinForce()
}