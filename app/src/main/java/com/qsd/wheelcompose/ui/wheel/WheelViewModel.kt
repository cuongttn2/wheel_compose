package com.qsd.wheelcompose.ui.wheel

import com.qsd.wheelcompose.base.BaseViewModel
import com.qsd.wheelcompose.model.data.local.prefs.AppPrefs
import com.qsd.wheelcompose.ui.wheel.WheelIntent.ClearDefaultNames
import com.qsd.wheelcompose.ui.wheel.WheelIntent.HandleWheelResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WheelViewModel @Inject constructor() : BaseViewModel() {
    private val _state = MutableStateFlow<UIStateWheel>(UIStateWheel())
    val state: StateFlow<UIStateWheel> = _state

    fun handleIntent(intent: WheelIntent) {
        when (intent) {
            is HandleWheelResult -> {
                handleWheelResult(name = intent.name)
            }

            is ClearDefaultNames -> {
                clearDefaultNames()
            }

            is WheelIntent.UpdateNames -> {
                updateNames(intent.names)
            }

            is WheelIntent.HideFirework -> {
                hideFirework()
            }

            is WheelIntent.ResetResultName -> {
                resetResultName()
            }

            is WheelIntent.HandleVisibleNamesField -> {
                handleVisibleNamesField(intent.visible)
            }

            is WheelIntent.StartSpin -> startSpin()
            is WheelIntent.ToggleVolume -> toggleVolume()

        }
    }

    private fun startSpin() {
        _state.update {
            it.copy(isPlaySound = true)
        }
    }

    private fun toggleVolume() {
        AppPrefs.isWheelSoundEnabled = !AppPrefs.isWheelSoundEnabled
        _state.update {
            it.copy(volumeOn = AppPrefs.isWheelSoundEnabled)
        }
    }

    private fun handleWheelResult(name: String) {
        _state.update {
            it.copy(resultName = name, playFirework = true, isPlaySound = false)
        }
    }

    private fun resetResultName() {
        _state.update {
            val newNames = it.names.filter { n -> n != it.resultName }.toMutableList()
            it.copy(names = newNames, resultName = null)
        }
    }

    private fun clearDefaultNames() {
        _state.update {
            it.copy(names = mutableListOf<String>())
        }
    }

    private fun updateNames(names: String) {
        val result = names.split("\\r?\\n".toRegex())
        _state.update {
            it.copy(names = result as MutableList<String>)
        }
    }

    private fun hideFirework() {
        _state.update {
            it.copy(playFirework = false)
        }
    }

    private fun handleVisibleNamesField(visible: Boolean) {
        _state.update {
            it.copy(visibleNamesField = visible)
        }
    }

}