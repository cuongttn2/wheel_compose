package com.qsd.wheelcompose.ui.first_language

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.onboard.OnboardActivity
import com.qsd.wheelcompose.utils.LocalViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstLanguageActivity : BaseActivity<FirstLanguageViewModel>() {
    override val viewModel: FirstLanguageViewModel by viewModels()
    override val titleTopBar: Int? = null

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        CompositionLocalProvider(
            LocalViewModelProvider provides viewModel
        ) {
            FirstLanguageScreen() {
                OnboardActivity.start(this@FirstLanguageActivity)
                finish()
            }
        }
    }

    override fun navigationIcon(): @Composable (() -> Unit) {
        return {}
    }

    override fun init(savedInstanceState: Bundle?) {}

    companion object {
        fun start(context: Context, bundle: Bundle? = null, flags: Int? = null) {
            val intent = Intent(context, FirstLanguageActivity::class.java)
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