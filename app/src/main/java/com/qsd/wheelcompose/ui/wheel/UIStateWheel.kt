package com.qsd.wheelcompose.ui.wheel

private val defaultWheelNames =
    mutableListOf<String>("One", "Two", "Three", "Four", "Five", "Six")

data class UIStateWheel(
    val playFirework: Boolean = false,
    val names: MutableList<String> = defaultWheelNames,
    val resultName: String? = null,
    val visibleNamesField: Boolean = true,
)