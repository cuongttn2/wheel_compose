package com.qsd.wheelcompose.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun HomeScreen(onItemClick: (Int) -> Unit) {
    val viewModel = LocalViewModelProvider.current as MainViewModel
    val uiSate = viewModel.state.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        items(uiSate.value.navButtons) { button ->
            HomeNavButton(button) {
                onItemClick.invoke(button.screenId)
            }
        }
    }
}