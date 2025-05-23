package com.qsd.wheelcompose.ui.wheel

sealed class WheelIntent {
    data class HandleWheelResult(val name: String) : WheelIntent()
    object ClearDefaultNames : WheelIntent()
    data class UpdateNames(val names: String) : WheelIntent()
    object HideFirework : WheelIntent()
    object ResetResultName : WheelIntent()
    data class HandleVisibleNamesField(val visible: Boolean) : WheelIntent()
    object StartSpin : WheelIntent()
    object ToggleVolume : WheelIntent()
}