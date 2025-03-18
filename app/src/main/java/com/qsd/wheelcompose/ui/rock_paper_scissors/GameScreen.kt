package com.qsd.wheelcompose.ui.rock_paper_scissors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.ui.theme.PressStart2PFontFamily
import com.qsd.wheelcompose.ui.theme.btn_start_rps_background
import com.qsd.wheelcompose.ui.theme.btn_start_rps_text_color
import com.qsd.wheelcompose.ui.theme.typography
import com.qsd.wheelcompose.ui.widget.ThreeDimensionalLayout
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun GameScreen() {
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
    val resultName = uiState.value.resultName
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
            resultName = resultName,
            isPlaying = isPlaying,
            rpsList = rpsList,
            currentIndexP1 = currentIndexP1,
            currentIndexP2 = currentIndexP2,
            finalChoiceP1 = finalChoiceP1,
            finalChoiceP2 = finalChoiceP2
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nút Start
        ThreeDimensionalLayout(
            edgeOffset = 10.dp,
            onClick = { viewModel.handleIntent(RPSIntent.StartPlay) }) {
            Box(
                modifier = Modifier
                    .background(color = btn_start_rps_background)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.start),
                    color = btn_start_rps_text_color,
                    style = typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        fontFamily = PressStart2PFontFamily
                    )
                )
            }
        }
    }
}