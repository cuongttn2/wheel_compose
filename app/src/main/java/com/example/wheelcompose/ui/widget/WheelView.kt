import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.example.wheelcompose.ui.utils.Utils.assignColorsToSegments
import timber.log.Timber

@Composable
fun WheelSpinner(
    segments: List<String>,
    modifier: Modifier = Modifier,
    availableColors: List<Color> = listOf(
        Color.Blue,
        Color.Yellow,
        Color.Magenta,
        Color.Cyan,
        Color.Green,
        Color.Red
    ),
) {
    val segmentColors = assignColorsToSegments(segments.size, availableColors)

    /**
    Góc quét cho mỗi phần
     */
    val sweepAngle = 360f / segments.size

    Canvas(modifier = modifier) {
        /**
        Tính bán kính và tâm của canvas
         */
        val radius = size.minDimension / 2f
        val center = Offset(x = size.width / 2, y = size.height / 2)

        segments.forEachIndexed { index, _ ->
            /**
            Theo mặc định, góc 0° tương ứng với vị trí 3 giờ trên đồng hồ và các góc tăng dần theo chiều kim đồng hồ.
            Cộng dồn góc bắt đầu của mỗi phần (trừ 90 độ để bắt đầu từ phía trên)
             */
            val startAngle = sweepAngle * index - 90f
            Timber.tag("WheelSpinner")
                .d("sweepAngle: ${sweepAngle}, index: ${index}, startAngle: $startAngle")
            drawArc(
                color = segmentColors[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true, // Vẽ thành hình quạt (pie)
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2)
            )
        }
    }
}