package com.qsd.wheelcompose.ui.setting

import com.qsd.wheelcompose.model.data.local.ui.NavButtonItems

data class UIStateSetting(
    val navButtons: MutableList<NavButtonItems> = mutableListOf<NavButtonItems>(),
)