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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun WheelScreen(
    segments: List<String>,
    durationMillis: Int = 3000,  // thời gian quay có thể điều chỉnh (mili giây)
    onResult: (String) -> Unit = {} // Callback trả về tên phần được chọn
) {
    // Animatable để điều chỉnh góc quay của wheel
    val rotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    // Mỗi phần có góc quét bằng 360° chia đều theo số lượng phần
    val sweepAngle = 360f / segments.size

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Center
    ) {
        // WheelSpinner được quay dựa trên giá trị rotation.value (đơn vị độ)
        WheelSpinner(
            segments = segments,
            modifier = Modifier
                .size(300.dp)
                .rotate(rotation.value)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                // Tính toán số vòng quay ngẫu nhiên và một góc quay phụ
                val fullSpins = (3..5).random() // quay từ 3 đến 5 vòng
                val extraRotation = Random.nextFloat() * 360f
                val targetRotation = rotation.value + 360f * fullSpins + extraRotation
                // Quay wheel với hiệu ứng mượt, thời gian có thể điều chỉnh
                rotation.animateTo(
                    targetRotation,
                    animationSpec = tween(durationMillis = durationMillis, easing = FastOutSlowInEasing)
                )
                // Sau khi animation hoàn thành, tính phần nào đang ở vị trí top (góc -90°)
                // Lưu ý: khi wheel quay, góc của phần được tính theo:
                // effectiveAngle = (360 - (finalRotation % 360)) % 360
                val finalRotation = rotation.value % 360
                val effectiveAngle = (360 - finalRotation) % 360
                // selectedIndex: phần nào có khoảng góc chứa effectiveAngle (với góc quét sweepAngle)
                val selectedIndex = (effectiveAngle / sweepAngle).toInt() % segments.size
                // Trả về tên của segment được chọn
                onResult(segments[selectedIndex])
            }
        }) {
            Text("Spin")
        }
    }
}