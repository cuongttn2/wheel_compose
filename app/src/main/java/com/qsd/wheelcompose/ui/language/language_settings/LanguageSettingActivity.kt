package com.qsd.wheelcompose.ui.language.language_settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.EventManager
import com.qsd.wheelcompose.ui.EventManager.AppEvent
import com.qsd.wheelcompose.ui.main.MainActivity
import com.qsd.wheelcompose.ui.widget.NavBackIcon
import com.qsd.wheelcompose.utils.LocalViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LanguageSettingActivity : BaseActivity<LanguageSettingViewModel>() {
    override val viewModel: LanguageSettingViewModel by viewModels<LanguageSettingViewModel>()
    override val titleTopBar: Int = R.string.app_language

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState
    ) {
        CompositionLocalProvider(
            LocalViewModelProvider provides viewModel
        ) {
            LanguageSettingScreen()
        }
    }

    override fun navigationIcon(): @Composable (() -> Unit) {
        return {
            NavBackIcon { finish() }
        }
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    @Composable
    override fun ObserveEventFlow() {
        LaunchedEffect(EventManager) {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    EventManager.eventsFlow.collect { event ->
                        when (event) {
                            is AppEvent.RefreshAppLanguage -> {
                                val flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                MainActivity.start(this@LanguageSettingActivity, flags = flags)
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun start(context: Context, bundle: Bundle? = null, flags: Int? = null) {
            val intent = Intent(context, LanguageSettingActivity::class.java)
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