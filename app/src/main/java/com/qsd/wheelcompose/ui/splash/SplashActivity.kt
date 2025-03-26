package com.qsd.wheelcompose.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.first_language.FirstLanguageActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel>() {
    override val viewModel: SplashViewModel by viewModels()

    override val titleTopBar: Int? = null

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        SplashScreen()
        LaunchedEffect(Unit) {
            delay(3000)
            FirstLanguageActivity.start(this@SplashActivity)
            finish()
        }
    }

    override fun navigationIcon(): @Composable (() -> Unit) {
        return {}
    }

    override fun init(savedInstanceState: Bundle?) {

    }

}