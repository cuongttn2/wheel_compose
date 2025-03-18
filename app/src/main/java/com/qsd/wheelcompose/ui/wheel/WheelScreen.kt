package com.qsd.wheelcompose.ui.wheel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.ui.dialog.SpinResult
import com.qsd.wheelcompose.ui.widget.FireworkAnimation
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun WheelScreen(
    durationMillis: Int = 3000,
) {
    val viewModel = LocalViewModelProvider.current as WheelViewModel
    val uiState = viewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        GameScreen(durationMillis)

        FireworkAnimation(
            isVisible = uiState.value.playFirework,
            modifier = Modifier.fillMaxSize(),
            onAnimationFinished = { viewModel.handleIntent(WheelIntent.HideFirework) }
        )

        if (uiState.value.resultName != null) {
            SpinResult(uiState.value.resultName!!) {
                viewModel.handleIntent(WheelIntent.ResetResultName)
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    WheelScreen()
}
