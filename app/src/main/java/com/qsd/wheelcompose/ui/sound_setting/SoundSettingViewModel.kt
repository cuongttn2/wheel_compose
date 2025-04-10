package com.qsd.wheelcompose.ui.sound_setting

import com.qsd.wheelcompose.base.BaseViewModel
import com.qsd.wheelcompose.model.data.local.prefs.AppPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SoundSettingViewModel @Inject constructor() : BaseViewModel() {

    private val _uiState = MutableStateFlow(
        SoundSettingUiState(
            rpsSoundEnabled = AppPrefs.isRpsSoundEnabled,
            wheelSoundEnabled = AppPrefs.isWheelSoundEnabled
        )
    )
    val uiState: StateFlow<SoundSettingUiState> = _uiState

    fun toggleRpsSound(enabled: Boolean) {
        AppPrefs.isRpsSoundEnabled = enabled
        _uiState.update { it.copy(rpsSoundEnabled = enabled) }
    }

    fun toggleWheelSound(enabled: Boolean) {
        AppPrefs.isWheelSoundEnabled = enabled
        _uiState.update { it.copy(wheelSoundEnabled = enabled) }
    }
}