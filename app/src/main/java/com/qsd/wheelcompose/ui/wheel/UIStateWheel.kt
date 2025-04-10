package com.qsd.wheelcompose.ui.wheel

import com.qsd.wheelcompose.model.data.local.prefs.AppPrefs

private val defaultWheelNames =
    mutableListOf<String>("Alice", "Bob", "Charlie", "David", "Eve", "Frank")

data class UIStateWheel(
    val playFirework: Boolean = false,
    val names: MutableList<String> = defaultWheelNames,
    val resultName: String? = null,
    val visibleNamesField: Boolean = true,
    val isPlaySound: Boolean = false,
    val volumeOn: Boolean = AppPrefs.isWheelSoundEnabled,
)