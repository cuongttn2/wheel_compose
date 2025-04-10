package com.qsd.wheelcompose.ui.language.language_first

import android.content.Context
import com.qsd.wheelcompose.model.data.local.prefs.AppPrefs
import com.qsd.wheelcompose.ui.EventManager
import com.qsd.wheelcompose.ui.EventManager.AppEvent
import com.qsd.wheelcompose.ui.language.BaseLanguageViewModel
import com.qsd.wheelcompose.ui.language.LanguageIntent
import com.qsd.wheelcompose.utils.DEFAULT_LANGUAGE_CODE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LanguageFirstViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    BaseLanguageViewModel(context) {

    init {
        initLanguage()
    }

    override fun handleIntent(intent: LanguageIntent) {
        super.handleIntent(intent)
        if (intent is LanguageIntent.NavigateScreen) {
            AppPrefs.language = _state.value.languages.firstOrNull { it.check == true }?.code
                ?: DEFAULT_LANGUAGE_CODE
            EventManager.triggerEvent(AppEvent.NavigateScreen)
        }
    }
}