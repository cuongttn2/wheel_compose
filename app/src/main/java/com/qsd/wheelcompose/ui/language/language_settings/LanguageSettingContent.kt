package com.qsd.wheelcompose.ui.language.language_settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.ui.language.LanguageIntent
import com.qsd.wheelcompose.ui.language.LanguageItem
import com.qsd.wheelcompose.utils.LocalViewModelProvider

@Composable
fun LanguageSettingContent(
    modifier: Modifier = Modifier,
) {
    val viewModel = LocalViewModelProvider.current as LanguageSettingViewModel
    val uiState = viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            itemsIndexed(
                items = uiState.value.languages,
                key = { index, item -> item.id }) { _, item ->
                LanguageItem(item) {
                    viewModel.handleIntent(LanguageIntent.SelectLanguage(it))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                viewModel.handleIntent(LanguageIntent.RefreshLanguage)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.done_select_language))
        }
    }
}