package com.qsd.wheelcompose.ui.wheel

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.ui.wheel.WheelIntent.UpdateNames
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun GameScreen(durationMillis: Int) {
    val viewModel = LocalViewModelProvider.current as WheelViewModel
    val uiState = viewModel.state.collectAsState()
    var spinForce by remember { mutableFloatStateOf(0f) }
    val rotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val segments = uiState.value.names.filter { s -> !s.isEmpty() }
    val sweepAngle = 360f / segments.size

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        WheelSpinner(
            segments = segments,
            modifier = Modifier
                .size(300.dp)
                .rotate(rotation.value),
        )

        Spacer(modifier = Modifier.height(16.dp))
        ForceSlider(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.6f)
                .height(20.dp),
            force = spinForce,
            onForceChange = { spinForce = it },
            onForceChangeFinished = {
                spinWheel(
                    coroutineScope = coroutineScope,
                    rotation = rotation,
                    segments = segments,
                    spinForce = spinForce,
                    sweepAngle = sweepAngle,
                    onResetSpinForce = { spinForce = 0f },
                    durationMillis = durationMillis,
                    onHandleResult = { viewModel.handleIntent(WheelIntent.HandleWheelResult(name = it)) }
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        NamesArea(
            text = uiState.value.names.joinToString("\n"),
            visibleNamesField = uiState.value.visibleNamesField,
            onClearNames = {
                viewModel.handleIntent(WheelIntent.ClearDefaultNames)
            },
            onVisibleNamesField = {
                viewModel.handleIntent(WheelIntent.HandleVisibleNamesField(!uiState.value.visibleNamesField))
            },
            onValueChange = { viewModel.handleIntent(UpdateNames(it)) }
        )
    }
}