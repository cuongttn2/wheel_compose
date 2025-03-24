package com.qsd.wheelcompose.ui.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalPagerIndicator(
    pagerState: androidx.compose.foundation.pager.PagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()
    ) {
        for (i in 0 until pagerState.pageCount) {
            val color = if (i == pagerState.currentPage) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = color)
            )
        }
    }
}