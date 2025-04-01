package com.qsd.wheelcompose.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.about.AboutAppActivity
import com.qsd.wheelcompose.ui.language.language_settings.LanguageSettingActivity
import com.qsd.wheelcompose.ui.privacy.PrivacyActivity
import com.qsd.wheelcompose.ui.sound_setting.SoundSettingActivity
import com.qsd.wheelcompose.ui.widget.NavBackIcon
import com.qsd.wheelcompose.utils.LocalViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity<SettingViewModel>() {
    override val viewModel: SettingViewModel by viewModels()

    override val titleTopBar = R.string.settings

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        CompositionLocalProvider(
            LocalViewModelProvider provides viewModel
        ) {
            SettingScreen {
                navToScreen(it)
            }
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

    }

    companion object {
        fun start(context: Context, bundle: Bundle? = null, flags: Int? = null) {
            val intent = Intent(context, SettingActivity::class.java)
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
            SettingNavButtonID.Sound.ordinal -> {
                SoundSettingActivity.start(this@SettingActivity)
            }

            SettingNavButtonID.Language.ordinal -> {
                LanguageSettingActivity.start(this@SettingActivity)
            }

            SettingNavButtonID.About.ordinal -> {
                AboutAppActivity.start(this@SettingActivity)
            }

            SettingNavButtonID.Privacy.ordinal -> {
                PrivacyActivity.start(this@SettingActivity)
            }
        }
    }

}