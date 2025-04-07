package com.qsd.wheelcompose.ui.rock_paper_scissors

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
class RPSActivity : BaseActivity<RPSViewModel>() {
    override val viewModel: RPSViewModel by viewModels()
    override val titleTopBar = R.string.rock_paper_scissors
    private var mediaPlayer: MediaPlayer? = null

    @Composable
    override fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    ) {
        CompositionLocalProvider(
            LocalViewModelProvider provides viewModel
        ) {
            RockPaperScissorsScreen()
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
        LaunchedEffect(uiState.value.isPlaying) {

            if (uiState.value.isPlaying && uiState.value.volumeOn) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(this@RPSActivity, R.raw.rps_sound_bg).apply {
                    isLooping = false
                    start()
                }
            } else {
                resetMediaPlayer()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        resetMediaPlayer()
    }

    fun resetMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
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