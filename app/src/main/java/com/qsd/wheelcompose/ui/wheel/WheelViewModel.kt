package com.qsd.wheelcompose.ui.wheel

import com.qsd.wheelcompose.base.BaseViewModel
import com.qsd.wheelcompose.ui.wheel.WheelIntent.ClearDefaultNames
import com.qsd.wheelcompose.ui.wheel.WheelIntent.HandleWheelResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WheelViewModel @Inject constructor() : BaseViewModel() {
    private val _state = MutableStateFlow<UIWheelState>(UIWheelState())
    val state: StateFlow<UIWheelState> = _state

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

        }
    }

    private fun handleWheelResult(name: String) {
        _state.update {
            it.copy(resultName = name, showFirework = true)
        }
    }

    private fun resetResultName() {
        _state.update {
            it.copy(resultName = null)
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
            it.copy(showFirework = false)
        }
    }

}