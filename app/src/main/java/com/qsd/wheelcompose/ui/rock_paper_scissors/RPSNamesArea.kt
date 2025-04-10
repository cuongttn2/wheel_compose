package com.qsd.wheelcompose.ui.rock_paper_scissors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RPSNamesArea(
    nameP1: String,
    nameP2: String,
    onNameP1Change: (String) -> Unit,
    onNameP2Change: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = nameP1,
            onValueChange = { onNameP1Change.invoke(it) },
            label = { Text("P1") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        TextField(
            value = nameP2,
            onValueChange = { onNameP2Change.invoke(it) },
            label = { Text("P2") },
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
    }
}