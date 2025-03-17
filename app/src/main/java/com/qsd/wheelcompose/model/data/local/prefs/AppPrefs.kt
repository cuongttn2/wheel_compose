package com.qsd.wheelcompose.model.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object AppPrefs {
    private lateinit var prefs: SharedPreferences
    private val LOCK = Any()
    private val gson: Gson by lazy { Gson() }
    fun init(context: Context) {
        if (!AppPrefs::prefs.isInitialized) {
            synchronized(LOCK) {
                prefs = PreferenceHelper.newEncryptPrefs(context, "wheel_compose_prefs")
            }
        }
    }

    fun isInitialized() = AppPrefs::prefs.isInitialized

    var language: String
        set(value) {
            prefs[APP_LANGUAGE] = value
        }
        get() = prefs[APP_LANGUAGE, "en"] ?: "en"


    private const val APP_LANGUAGE = "app_language_code"

}