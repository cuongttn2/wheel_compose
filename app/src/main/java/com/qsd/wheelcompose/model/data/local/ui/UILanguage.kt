package com.qsd.wheelcompose.model.data.local.ui

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.qsd.wheelcompose.utils.DEFAULT_LANGUAGE_CODE
import com.qsd.wheelcompose.utils.DEFAULT_LANGUAGE_NAME
import java.util.UUID

@Stable
data class UILanguage(
    val id: String = UUID.randomUUID().toString(),
    val code: String = DEFAULT_LANGUAGE_CODE,
    val name: String = DEFAULT_LANGUAGE_NAME,
    val flag: Int = 0,
    val check: Boolean = false,
    val background: Color = Color.Transparent
)