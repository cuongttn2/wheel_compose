package com.qsd.wheelcompose.ui.first_language

sealed class LanguageIntent {
    data class SelectLanguage(val code: String) : LanguageIntent()
    object NavigateScreen : LanguageIntent()
}