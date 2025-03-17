package com.qsd.wheelcompose.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.qsd.wheelcompose.utils.FIREWORK_ANIM_NAME
import timber.log.Timber

@Composable
fun FireworkAnimation(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit = {},
) {
    if (isVisible) {
        // Load file JSON animation từ thư mục assets (đảm bảo file "fireworks.json" đã được đặt vào src/main/assets)
        val composition by rememberLottieComposition(LottieCompositionSpec.Asset(FIREWORK_ANIM_NAME))
        val animationState = animateLottieCompositionAsState(
            composition = composition, iterations = 1, // Chạy một vòng
            speed = 1f, isPlaying = true
        )

        // Sử dụng LaunchedEffect để theo dõi tiến trình và gọi callback khi animation gần hoàn thành
        LaunchedEffect(animationState.progress) {
            if (animationState.progress >= 0.99f) {
                onAnimationFinished()
            }
        }

        LottieAnimation(
            modifier = modifier,
            contentScale = ContentScale.FillHeight,
            composition = composition, progress = { animationState.progress },
        )
    }
}