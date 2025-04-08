package com.qsd.wheelcompose.ui.language

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.base.BaseViewModel
import com.qsd.wheelcompose.model.data.local.prefs.AppPrefs
import com.qsd.wheelcompose.model.data.local.ui.UILanguage
import com.qsd.wheelcompose.ui.theme.bg_language_selected
import com.qsd.wheelcompose.ui.theme.bg_language_unselected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.UUID

abstract class BaseLanguageViewModel(
    private val context: Context
) : BaseViewModel() {

    protected val _state = MutableStateFlow(UILanguageState())
    val state: StateFlow<UILanguageState> = _state

    open fun handleIntent(intent: LanguageIntent) {
        when (intent) {
            is LanguageIntent.SelectLanguage -> selectLanguage(intent.code)
            else -> {}
        }
    }

    protected fun initLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
            val names = context.resources.getStringArray(R.array.languages_name)
            val codes = context.resources.getStringArray(R.array.languages_code)
            val ta = context.resources.obtainTypedArray(R.array.languages_flag)
            val icons = IntArray(ta.length()) { ta.getResourceId(it, 0) }

            val list = names.mapIndexed { index, name ->
                UILanguage(
                    id = UUID.randomUUID().toString(),
                    flag = icons[index],
                    name = name,
                    code = codes[index],
                    check = (codes[index] == AppPrefs.language),
                    background = if (codes[index] == AppPrefs.language) bg_language_selected else bg_language_unselected
                )
            }

            ta.recycle()

            withContext(Dispatchers.Main) {
                _state.update { it.copy(languages = list) }
            }
        }
    }

    private fun selectLanguage(code: String) {
        Timber.d("select language code: $code")
        val updated = _state.value.languages.map {
            it.copy(
                check = (it.code == code),
                background = if (it.code == code) bg_language_selected else bg_language_unselected
            )
        }
        _state.update { it.copy(languages = updated) }
    }
}
