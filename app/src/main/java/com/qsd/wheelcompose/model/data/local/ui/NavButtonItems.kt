package com.qsd.wheelcompose.model.data.local.ui

import androidx.annotation.DrawableRes

data class NavButtonItems(
    @DrawableRes val icon: Int,
    val name: String,
    val screenId: Int,
)
