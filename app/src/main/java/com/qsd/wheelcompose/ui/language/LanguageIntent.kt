package com.qsd.wheelcompose.ui.language

sealed class LanguageIntent {
    data class SelectLanguage(val code: String) : LanguageIntent()
    object NavigateScreen : LanguageIntent()
    object RefreshLanguage : LanguageIntent()
}