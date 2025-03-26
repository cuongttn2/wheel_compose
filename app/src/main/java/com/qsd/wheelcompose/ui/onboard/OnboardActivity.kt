package com.qsd.wheelcompose.ui.onboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity<OnboardViewModel>() {
    override val viewModel: OnboardViewModel by viewModels()
    override val titleTopBar: Int? = null

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        OnboardScreen() {
            MainActivity.start(this@OnboardActivity)
            finish()
        }
    }

    override fun navigationIcon(): @Composable (() -> Unit) {
        return {}
    }

    override fun init(savedInstanceState: Bundle?) {}


    @Composable
    override fun ObserveEventFlow() {

    }

    companion object {
        fun start(context: Context, bundle: Bundle? = null, flags: Int? = null) {
            val intent = Intent(context, OnboardActivity::class.java)
            bundle?.let {
                intent.putExtras(it)
            }
            flags?.let {
                intent.flags = flags
            }
            context.startActivity(intent)
        }
    }

}