package com.example.wheelcompose.utils

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.wheelcompose.base.BaseViewModel

val LocalViewModelProvider = staticCompositionLocalOf<BaseViewModel> {
    error("No ViewModel found")
}
