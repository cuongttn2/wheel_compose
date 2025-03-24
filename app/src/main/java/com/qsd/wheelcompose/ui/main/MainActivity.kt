package com.qsd.wheelcompose.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.rock_paper_scissors.RPSActivity
import com.qsd.wheelcompose.ui.setting.SettingActivity
import com.qsd.wheelcompose.ui.wheel.WheelActivity
import com.qsd.wheelcompose.utils.LocalViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    override val titleTopBar = R.string.home

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        scaffoldState: SnackbarHostState,
    ) {
        CompositionLocalProvider(
            LocalViewModelProvider provides viewModel
        ) {
            HomeScreen {
                navToScreen(it)
            }
        }

    }

    override fun navigationIcon(): @Composable (() -> Unit) {
        return {}
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    companion object {
        fun start(context: Context, bundle: Bundle? = null, flags: Int? = null) {
            val intent = Intent(context, MainActivity::class.java)
            bundle?.let {
                intent.putExtras(it)
            }
            flags?.let {
                intent.flags = flags
            }
            context.startActivity(intent)
        }
    }

    private fun navToScreen(screenId: Int) {
        when (screenId) {
            HomeNavButtonID.Wheel.ordinal -> {
                WheelActivity.start(this@MainActivity)
            }

            HomeNavButtonID.RockHand.ordinal -> {
                RPSActivity.start(this@MainActivity)
            }

            HomeNavButtonID.Setting.ordinal -> {
                SettingActivity.start(this@MainActivity)
            }
        }
    }

}