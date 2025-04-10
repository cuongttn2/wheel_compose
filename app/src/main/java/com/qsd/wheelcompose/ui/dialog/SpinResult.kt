package com.qsd.wheelcompose.ui.dialog

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.qsd.wheelcompose.ui.theme.typography

@Composable
fun SpinResult(
    name: String,
    onDismiss: () -> Unit,
) {
    var currentProgress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(durationMillis = 6000)
        ) { value, _ ->
            currentProgress = value
        }
        onDismiss()
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.6f)
                    .wrapContentHeight()
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .clip(shape = RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                    .padding(8.dp)
            ) {
                val (txtName, btnClose, progressbar) = createRefs()

                IconButton(
                    modifier = Modifier
                        .size(24.dp)
                        .constrainAs(btnClose) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        },
                    onClick = onDismiss
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Close"
                    )
                }

                Text(
                    text = name,
                    style = typography.titleMedium,
                    maxLines = 3,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(txtName) {
                            top.linkTo(btnClose.bottom, margin = 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                LinearProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(progressbar) {
                            top.linkTo(txtName.bottom, margin = 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }
        }
    }

}


@Composable
@Preview(showSystemUi = true)
private fun Prev() {
    SpinResult(name = "hello") {}
}