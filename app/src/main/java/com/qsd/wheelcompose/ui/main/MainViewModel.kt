package com.qsd.wheelcompose.ui.main

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.base.BaseViewModel
import com.qsd.wheelcompose.model.data.local.ui.NavButtonItems
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    BaseViewModel() {
    private val _state = MutableStateFlow<UIStateHome>(UIStateHome())
    val state: StateFlow<UIStateHome> = _state

    init {
        initNavButton()
    }

    private fun initNavButton() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<NavButtonItems>()
            val names: Array<String> =
                context.resources.getStringArray(R.array.home_nav_btn_name)
            val ta = context.resources.obtainTypedArray(R.array.home_nav_btn_icon)
            val icons = IntArray(ta.length()) { i ->
                ta.getResourceId(i, 0)

            }
            names.forEachIndexed { index, string ->
                list.add(
                    NavButtonItems(
                        icon = icons[index],
                        name = string,
                        screenId = HomeNavButtonID.entries[index].ordinal
                    )
                )
            }
            ta.recycle()
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(navButtons = list)
                }
            }
        }
    }

}
