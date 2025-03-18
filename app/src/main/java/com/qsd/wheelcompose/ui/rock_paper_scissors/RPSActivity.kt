package com.qsd.wheelcompose.ui.rock_paper_scissors

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.widget.Greeting
import com.qsd.wheelcompose.ui.widget.NavBackIcon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RPSActivity : BaseActivity<RPSViewModel>() {
    override val viewModel: RPSViewModel by viewModels()
    override val titleTopBar = R.string.rock_paper_scissors

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        Greeting("Screen") {}
    }

    override fun navigationIcon(): @Composable (() -> Unit) {
        return {
            NavBackIcon { finish() }
        }
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    companion object {
        fun start(context: Context, bundle: Bundle? = null, flags: Int? = null) {
            val intent = Intent(context, RPSActivity::class.java)
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