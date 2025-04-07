package com.qsd.wheelcompose.ui.wheel

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.base.BaseActivity
import com.qsd.wheelcompose.ui.widget.NavBackIcon
import com.qsd.wheelcompose.utils.LocalViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WheelActivity : BaseActivity<WheelViewModel>() {
    override val viewModel: WheelViewModel by viewModels()
    override val titleTopBar = R.string.wheel
    private var mediaPlayer: MediaPlayer? = null

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        CompositionLocalProvider(
            LocalViewModelProvider provides viewModel
        ) {
            WheelScreen()
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
        val uiState = viewModel.state.collectAsState()
        LaunchedEffect(uiState.value.volumeOn) {
            if (uiState.value.volumeOn) {
                mediaPlayer?.setVolume(1f, 1f)
            } else {
                mediaPlayer?.setVolume(0f, 0f)
            }
        }
        LaunchedEffect(uiState.value.isPlaySound) {
            if (uiState.value.isPlaySound && uiState.value.volumeOn) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(this@WheelActivity, R.raw.rps_sound_bg).apply {
                    isLooping = false
                    start()
                }
            } else {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
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