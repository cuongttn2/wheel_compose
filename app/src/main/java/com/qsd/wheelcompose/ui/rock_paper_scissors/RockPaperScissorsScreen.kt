package com.qsd.wheelcompose.ui.rock_paper_scissors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun RockPaperScissorsScreen() {
    val viewModel = LocalViewModelProvider.current as RPSViewModel
    val uiState = viewModel.state.collectAsState()
    val rpsList = uiState.value.rpsList
    val nameP1 = uiState.value.nameP1
    val nameP2 = uiState.value.nameP2
    val isPlaying = uiState.value.isPlaying
    val currentIndexP1 = uiState.value.currentIndexP1
    val currentIndexP2 = uiState.value.currentIndexP2
    val finalChoiceP1 = uiState.value.finalChoiceP1
    val finalChoiceP2 = uiState.value.finalChoiceP2
    val result = uiState.value.result

    val isResultShown = uiState.value.isResultShown

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.ic_volume_off),
                contentDescription = "volume"
            )
        }

        RPSNamesArea(
            nameP1 = nameP1,
            nameP2 = nameP2,
            onNameP1Change = {
                viewModel.handleIntent(RPSIntent.NameP1Change(it))
            },
            onNameP2Change = {
                viewModel.handleIntent(RPSIntent.NameP2Change(it))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hàng hiển thị ô kết quả P1 - P2
        RPGPlayingResult(
            isPlaying = isPlaying,
            isResultShown = isResultShown,
            rpsList = rpsList,
            currentIndexP1 = currentIndexP1,
            currentIndexP2 = currentIndexP2,
            finalChoiceP1 = finalChoiceP1,
            finalChoiceP2 = finalChoiceP2
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Khu vực hiển thị kết quả (có thể giữ nguyên hiển thị text cho kết quả tổng quát)
        if (isResultShown) {
            Text(
                text = "Kết quả: $result",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút Start
        Button(
            onClick = {
                viewModel.handleIntent(RPSIntent.StartPlay)
            }
        ) {
            Text(text = "Start")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RockPaperScissorsScreenPreview() {
    RockPaperScissorsScreen()
}

