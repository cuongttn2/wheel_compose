package com.qsd.wheelcompose.ui.rock_paper_scissors

sealed class RPSIntent {
    object StartPlay : RPSIntent()
    data class NameP1Change(val name: String) : RPSIntent()
    data class NameP2Change(val name: String) : RPSIntent()
}