package com.qsd.wheelcompose.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.model.data.local.ui.NavButtonItems
import com.qsd.wheelcompose.ui.theme.typography

@Composable
fun HomeNavButton(item: NavButtonItems, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(64.dp),
            painter = painterResource(item.icon), contentDescription = "button ${item.name}"
        )
        Text(text = item.name, style = typography.bodySmall, textAlign = TextAlign.Center)
    }
}