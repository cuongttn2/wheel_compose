package com.qsd.wheelcompose.ui.rock_paper_scissors

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.utils.Utils.getDrawableForChoice

@Composable
fun RPGPlayingResult(
    isPlaying: Boolean,
    isResultShown: Boolean,
    rpsList: List<String>,
    currentIndexP1: Int,
    currentIndexP2: Int,
    finalChoiceP1: String,
    finalChoiceP2: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Ô của người chơi P1
        Box(
            modifier = Modifier
                .height(120.dp)
                .weight(1f)
                .border(2.dp, Color.LightGray)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isPlaying) {
                Crossfade(targetState = rpsList[currentIndexP1]) { choice ->
                    Image(
                        painter = painterResource(id = getDrawableForChoice(choice)),
                        contentDescription = choice,
                        modifier = Modifier.size(80.dp)
                    )
                }
            } else {
                if (isResultShown) {
                    Image(
                        painter = painterResource(id = getDrawableForChoice(finalChoiceP1)),
                        contentDescription = finalChoiceP1,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Ô của người chơi P2
        Box(
            modifier = Modifier
                .height(120.dp)
                .weight(1f)
                .border(2.dp, Color.LightGray)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isPlaying) {
                Crossfade(targetState = rpsList[currentIndexP2]) { choice ->
                    Image(
                        painter = painterResource(id = getDrawableForChoice(choice)),
                        contentDescription = choice,
                        modifier = Modifier.size(80.dp)
                    )
                }
            } else {
                if (isResultShown) {
                    Image(
                        painter = painterResource(id = getDrawableForChoice(finalChoiceP2)),
                        contentDescription = finalChoiceP2,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
        }
    }
}