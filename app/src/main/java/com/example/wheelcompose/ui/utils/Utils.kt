package com.example.wheelcompose.ui.utils

import androidx.compose.ui.graphics.Color

object Utils {

    fun assignColorsToSegments(segmentCount: Int, colors: List<Color>): List<Color> {
        // Lọc bỏ màu trắng và đen
        val filteredColors = colors.filter { it != Color.White && it != Color.Black }
        require(filteredColors.size >= 4) { "Cần ít nhất 4 màu (không bao gồm trắng và đen)" }
        val result = mutableListOf<Color>()

        // Gán màu cho phần đầu tiên
        result.add(filteredColors[0])

        // Gán màu cho các phần từ 1 đến n-2
        for (i in 1 until segmentCount - 1) {
            val prevColor = result[i - 1]
            // Chọn màu đầu tiên khác với màu của phần trước đó
            val chosenColor = filteredColors.first { it != prevColor }
            result.add(chosenColor)
        }

        // Với phần cuối cùng, cần đảm bảo không trùng với phần trước đó và phần đầu tiên (vì vòng kín)
        val prevColor = result.last()
        val firstColor = result.first()
        val availableForLast = filteredColors.filter { it != prevColor && it != firstColor }
        // Nếu có màu thỏa, chọn màu đầu tiên; nếu không, sử dụng màu dự phòng (ví dụ, màu thứ 4)
        val chosenForLast = if (availableForLast.isNotEmpty()) availableForLast.first() else filteredColors[3]
        result.add(chosenForLast)

        return result
    }
}