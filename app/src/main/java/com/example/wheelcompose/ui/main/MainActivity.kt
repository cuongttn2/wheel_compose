package com.example.wheelcompose.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.example.wheelcompose.base.BaseActivity
import com.example.wheelcompose.ui.wheel.WheelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    override val titleTopBar: String = "Main"

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        scaffoldState: SnackbarHostState
    ) {
        Greeting(name = "Android") {
            WheelActivity.start(this@MainActivity)
        }

    }

    override fun init(savedInstanceState: Bundle?) {

    }

}