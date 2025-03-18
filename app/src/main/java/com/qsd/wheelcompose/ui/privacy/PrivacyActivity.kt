package com.qsd.wheelcompose.ui.privacy

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
class PrivacyActivity : BaseActivity<PrivacyViewModel>() {
    override val viewModel: PrivacyViewModel by viewModels<PrivacyViewModel>()
    override val titleTopBar = R.string.privacy

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
            val intent = Intent(context, PrivacyActivity::class.java)
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