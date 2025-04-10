package com.qsd.wheelcompose.utils

import androidx.compose.runtime.staticCompositionLocalOf
import com.qsd.wheelcompose.base.BaseViewModel

val LocalViewModelProvider = staticCompositionLocalOf<BaseViewModel> {
    error("No ViewModel found")
}
