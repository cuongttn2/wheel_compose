package com.qsd.wheelcompose.ui.wheel

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.R

@Composable
fun NamesArea(
    modifier: Modifier = Modifier,
    text: String,
    visibleNamesField: Boolean,
    onVisibleNamesField: () -> Unit,
    onClearNames: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    var offset by remember { mutableFloatStateOf(0f) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .clip(shape = RoundedCornerShape(2.dp))
            .border(
                width = 1.dp, color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(2.dp)
            )
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = stringResource(R.string.input_name),
                fontWeight = FontWeight.Black,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = if (visibleNamesField) {
                    painterResource(R.drawable.ic_visibility)
                } else {
                    painterResource(R.drawable.ic_visibility_off)
                },
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "visibility",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onVisibleNamesField.invoke() }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Default.Delete,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "delete",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onClearNames.invoke() },
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        )
        if (visibleNamesField) {
            TextField(
                value = text,
                onValueChange = { onValueChange(it) },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .scrollable(
                        orientation = Orientation.Vertical,
                        state = rememberScrollableState { delta ->
                            offset += delta
                            delta
                        }
                    )
            )
        }
    }
}