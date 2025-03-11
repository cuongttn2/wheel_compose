package com.example.wheelcompose.ui.wheel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.example.wheelcompose.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WheelActivity : BaseActivity<WheelViewModel>() {
    override val viewModel: WheelViewModel by viewModels()
    override val titleTopBar: String = "WheelActivity"

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        WheelScreen()
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    companion object {
        fun start(context: Context, bundle: Bundle? = null, flags: Int? = null) {
            val intent = Intent(context, WheelActivity::class.java)
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