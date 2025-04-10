package com.qsd.wheelcompose.ui.rock_paper_scissors

import com.qsd.wheelcompose.model.data.local.prefs.AppPrefs

data class UIStateRPS(
    val rpsList: List<String> = listOf(
        RPSChoice.Scissors.name,
        RPSChoice.Rock.name,
        RPSChoice.Paper.name
    ),
    val nameP1: String = "P1",
    val nameP2: String = "P2",
    val resultName: String? = null,
    val showResult: Boolean = false,
    val isPlaySound: Boolean = false,
    val volumeOn: Boolean = AppPrefs.isRpsSoundEnabled,
    val isPlaying: Boolean = false,
    val playFirework: Boolean = false,
    val currentIndexP1: Int = 0,
    val currentIndexP2: Int = 0,
    val finalChoiceP1: String = "",
    val finalChoiceP2: String = "",
)
