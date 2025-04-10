package com.qsd.wheelcompose.ui.sound_setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun SoundSettingScreen() {
    val viewModel = LocalViewModelProvider.current as SoundSettingViewModel
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.enable_sound_for_rps))
            Switch(
                checked = uiState.value.rpsSoundEnabled,
                onCheckedChange = { viewModel.toggleRpsSound(it) }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.enable_sound_for_wheel))
            Switch(
                checked = uiState.value.wheelSoundEnabled,
                onCheckedChange = { viewModel.toggleWheelSound(it) }
            )
        }
    }
}