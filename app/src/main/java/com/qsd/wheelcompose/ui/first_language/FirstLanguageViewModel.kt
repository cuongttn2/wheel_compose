package com.qsd.wheelcompose.ui.first_language

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.base.BaseViewModel
import com.qsd.wheelcompose.model.data.local.prefs.AppPrefs
import com.qsd.wheelcompose.model.data.local.ui.UILanguage
import com.qsd.wheelcompose.ui.first_language.LanguageIntent.SelectLanguage
import com.qsd.wheelcompose.ui.theme.bg_language_selected
import com.qsd.wheelcompose.ui.theme.bg_language_unselected
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FirstLanguageViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    BaseViewModel() {
    private val _state = MutableStateFlow<UILanguageState>(UILanguageState())
    val state: StateFlow<UILanguageState> = _state

    init {
        initLanguage()
    }

    private fun initLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<UILanguage>()
            val names: Array<String> =
                context.resources.getStringArray(R.array.languages_name)
            val codes: Array<String> =
                context.resources.getStringArray(R.array.languages_code)
            val ta = context.resources.obtainTypedArray(R.array.languages_flag)
            val icons = IntArray(ta.length()) { i ->
                ta.getResourceId(i, 0)
            }

            names.forEachIndexed { index, string ->
                list.add(
                    UILanguage(
                        id = UUID.randomUUID().toString(),
                        flag = icons[index],
                        name = string,
                        code = codes[index],
                        check = (codes[index] == AppPrefs.language),
                        background = if (codes[index] == AppPrefs.language) {
                            bg_language_selected
                        } else bg_language_unselected
                    )
                )
            }
            ta.recycle()
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(languages = list)
                }
            }
        }
    }

    fun handleIntent(intent: LanguageIntent) {
        when (intent) {
            is SelectLanguage -> {
                selectLanguage(intent.code)
            }

        }
    }

    private fun selectLanguage(code: String) {
        Timber.d("select language code: $code")
        val newLanguage = _state.value.languages.toMutableList()
        newLanguage.replaceAll {
            if (it.code == code) {
                it.copy(check = true, background = bg_language_selected)
            } else {
                it.copy(check = false, background = bg_language_unselected)
            }
        }
        AppPrefs.language = code

        Timber.d("select language: $newLanguage")
        _state.update {
            it.copy(languages = newLanguage)
        }
    }

}