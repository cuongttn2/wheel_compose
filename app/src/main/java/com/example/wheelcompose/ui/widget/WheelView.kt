package com.example.wheelcompose.ui.widget

import android.graphics.Color.TRANSPARENT
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import com.example.wheelcompose.ui.theme.wheelColors
import com.example.wheelcompose.utils.Utils.assignColorsToSegments
import timber.log.Timber

@Composable
fun WheelSpinner(
    segments: List<String>,
    modifier: Modifier = Modifier,
    availableColors: List<Color> = wheelColors,
) {
    // Gán màu cho từng segment theo thuật toán đã định nghĩa (không chứa trắng, đen)
    val segmentColors = assignColorsToSegments(segments.size, availableColors)
    // Tính góc quét cho mỗi phần
    val sweepAngle = 360f / segments.size

    Canvas(modifier = modifier) {
        // Tính bán kính và tâm của canvas
        val radius = size.minDimension / 2f
        val center = Offset(x = size.width / 2, y = size.height / 2)

        // Tính kích thước chữ dựa trên bán kính
        val dynamicTextSize = radius * 0.15f

        // Tạo Paint để vẽ text trên nativeCanvas với kích thước chữ động
        val textPaint = Paint().apply {
//            color = android.graphics.Color.BLACK
            color = TRANSPARENT
            textSize = dynamicTextSize
            textAlign = Paint.Align.CENTER
        }

        segments.forEachIndexed { index, segmentName ->
            // Tính góc bắt đầu của segment, trừ 90° để bắt đầu từ vị trí 12h
            val startAngle = sweepAngle * index - 90f
            Timber.tag("WheelSpinner")
                .d("sweepAngle: $sweepAngle, index: $index, startAngle: $startAngle")

            // Vẽ arc cho từng segment
            drawArc(
                color = segmentColors[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2)
            )

            drawCircle(color = Color.White, center = center, radius = radius / 10)

            // Tính góc trung tâm của segment (tia phân giác)
            val midAngle = startAngle + sweepAngle / 2f
            // Xác định khoảng cách từ tâm đến vị trí vẽ text (điều chỉnh theo ý thích)
            val textRadius = radius * 0.6f

            // Vẽ text theo hướng của tia phân giác bằng cách xoay nativeCanvas
            drawContext.canvas.nativeCanvas.apply {
                save()
                // Dịch gốc tọa độ về tâm của wheel
                translate(center.x, center.y)
                // Xoay canvas theo midAngle (đơn vị độ)
                rotate(midAngle)
                // Vẽ text tại (textRadius, 0)
                // Với textAlign đã set CENTER, text sẽ được căn giữa theo trục hoành
                drawText(segmentName, textRadius, 0f, textPaint)
                restore()
            }
        }
    }
}
