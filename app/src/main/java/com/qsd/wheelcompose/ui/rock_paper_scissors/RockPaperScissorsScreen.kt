package com.qsd.wheelcompose.ui.rock_paper_scissors

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.ui.dialog.SpinResult
import com.qsd.wheelcompose.ui.widget.FireworkAnimation
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun RockPaperScissorsScreen() {
    val viewModel = LocalViewModelProvider.current as RPSViewModel
    val uiState = viewModel.state.collectAsState()
    val playFirework = uiState.value.playFirework

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        GameScreen()

        if (uiState.value.showResult) {
            SpinResult(uiState.value.resultName!!) {
                viewModel.handleIntent(RPSIntent.HideResultDialog)
            }
        }

        FireworkAnimation(
            isVisible = playFirework,
            modifier = Modifier.fillMaxSize(),
            onAnimationFinished = { viewModel.handleIntent(RPSIntent.HideFirework) }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun RockPaperScissorsScreenPreview() {
    RockPaperScissorsScreen()
}

