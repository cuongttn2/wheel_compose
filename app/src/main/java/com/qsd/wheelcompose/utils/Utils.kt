package com.qsd.wheelcompose.utils

import androidx.compose.ui.graphics.Color
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.ui.rock_paper_scissors.RPSChoice

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

    /**
     * Hàm ánh xạ chuỗi lựa chọn thành resource ID của hình ảnh trong drawable
     * Bạn cần có các file hình ảnh như ic_scissors, ic_rock, ic_paper trong thư mục res/drawable
     */
    fun getDrawableForChoice(choice: String): Int {
        return when (choice) {
            RPSChoice.Scissors.name -> R.drawable.ic_scissors_hand
            RPSChoice.Rock.name -> R.drawable.ic_rock_hand
            RPSChoice.Paper.name -> R.drawable.ic_paper_hand
            else -> R.drawable.ic_btn_rock_paper_scissors
        }
    }

    /**
     * Hàm so sánh để xác định thắng - thua - hòa
     */
    fun getGameResult(choiceA: String, choiceB: String, nameP1: String, nameP2: String): String {
        if (choiceA == choiceB) return "Tie"
        return when (choiceA) {
            RPSChoice.Scissors.name -> if (choiceB == RPSChoice.Rock.name) "$nameP2\nWin" else "$nameP1\nWin"
            RPSChoice.Rock.name -> if (choiceB == RPSChoice.Paper.name) "$nameP2\nWin" else "$nameP1\nWin"
            RPSChoice.Paper.name -> if (choiceB == RPSChoice.Scissors.name) "$nameP2\nWin" else "$nameP1\nWin"
            else -> ""
        }
    }

}