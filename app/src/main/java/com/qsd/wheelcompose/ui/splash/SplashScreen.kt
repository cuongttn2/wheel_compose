package com.qsd.wheelcompose.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.utils.T_REX_ANIM_NAME

@Composable
fun SplashScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(T_REX_ANIM_NAME))
    val animationState = animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever,
        speed = 1f, isPlaying = true, restartOnPlay = true
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "logo"
        )

        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.FillHeight,
            composition = composition, progress = { animationState.progress },
        )
    }
}