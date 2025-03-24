package com.qsd.wheelcompose.ui.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.R
import kotlinx.coroutines.launch

@Composable
fun OnboardScreen(onStart: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .weight(4f)
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> {
                    OnboardPage(
                        title = stringResource(R.string.spin_play),
                        description = stringResource(R.string.txt_intro_explore_your_game),
                        image = painterResource(id = R.drawable.ic_logo)
                    )
                }

                1 -> {
                    OnboardPage(
                        title = stringResource(R.string.txt_intro_lucky_wheel),
                        description = stringResource(R.string.txt_intro_try_your_luck_with_the_wheel),
                        image = painterResource(id = R.drawable.ic_logo)
                    )
                }

                2 -> {
                    OnboardPage(
                        title = stringResource(R.string.txt_intro_rock_paper_scissors),
                        description = stringResource(R.string.txt_intro_rps_description),
                        image = painterResource(id = R.drawable.ic_logo)
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Button(
                onClick = {
                    if (pagerState.currentPage < 2) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onStart.invoke()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage == 2) stringResource(R.string.txt_intro_start) else stringResource(
                        R.string.txt_intro_next
                    )
                )
            }

        }
        Spacer(modifier = Modifier.weight(3f))

    }
}


