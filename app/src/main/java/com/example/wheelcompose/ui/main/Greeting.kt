package com.example.wheelcompose.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Greeting(name: String, onStart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello $name!",
            fontSize = 24.sp,
            modifier = Modifier.wrapContentSize()
        )
        Button(onClick = {
            onStart()
        }) {
            Text(
                text = "Start",
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}