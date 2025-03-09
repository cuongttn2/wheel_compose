package com.example.wheelcompose.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wheelcompose.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    @Composable
    override fun BuiContent(savedInstanceState: Bundle?, scaffoldState: SnackbarHostState) {
        Greeting(
            name = "Android",
            modifier = Modifier.fillMaxSize()
        )
    }

    override fun init(savedInstanceState: Bundle?) {

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}