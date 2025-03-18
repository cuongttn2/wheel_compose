package com.qsd.wheelcompose.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun SettingScreen(onItemClick: (Int) -> Unit) {
    val viewModel = LocalViewModelProvider.current as SettingViewModel
    val uiSate = viewModel.state.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(uiSate.value.navButtons) { button ->
            SettingNavButton(button) {
                onItemClick.invoke(button.screenId)
            }
        }
    }
}