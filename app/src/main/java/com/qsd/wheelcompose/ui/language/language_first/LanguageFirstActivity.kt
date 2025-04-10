package com.qsd.wheelcompose.ui.language.language_first

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
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.EventManager
import com.qsd.wheelcompose.ui.EventManager.AppEvent
import com.qsd.wheelcompose.ui.onboard.OnboardActivity
import com.qsd.wheelcompose.utils.LocalViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LanguageFirstActivity : BaseActivity<LanguageFirstViewModel>() {
    override val viewModel: LanguageFirstViewModel by viewModels()
    override val titleTopBar: Int? = null


    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        CompositionLocalProvider(
            LocalViewModelProvider provides viewModel
        ) {
            FirstLanguageScreen()
        }
    }

    override fun navigationIcon(): @Composable (() -> Unit) {
        return {}
    }

    override fun init(savedInstanceState: Bundle?) {}

    @Composable
    override fun ObserveEventFlow() {
        LaunchedEffect(EventManager) {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    EventManager.eventsFlow.collect { event ->
                        when (event) {
                            is AppEvent.NavigateScreen -> {
                                OnboardActivity.start(this@LanguageFirstActivity)
                                finish()
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
            val intent = Intent(context, LanguageFirstActivity::class.java)
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