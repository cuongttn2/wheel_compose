package com.qsd.wheelcompose.ui.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.model.data.local.ui.UILanguage
import com.qsd.wheelcompose.utils.Utils.getLanguageName

@Composable
fun LanguageItem(
    item: UILanguage,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(item.code)
            }
            .background(item.background)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.flag),
            contentDescription = item.code,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(40.dp)
                .width(70.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = getLanguageName(item.code),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
