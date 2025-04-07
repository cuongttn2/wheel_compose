package com.qsd.wheelcompose.model.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.qsd.wheelcompose.utils.DEFAULT_LANGUAGE_CODE

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
        get() = prefs[APP_LANGUAGE, DEFAULT_LANGUAGE_CODE] ?: DEFAULT_LANGUAGE_CODE

    var isWheelSoundEnabled: Boolean
        set(value) {
            prefs[WHEEL_SOUND] = value
        }
        get() = prefs[WHEEL_SOUND, false] ?: false

    var isRpsSoundEnabled: Boolean
        set(value) {
            prefs[RPG_SOUND] = value
        }
        get() = prefs[RPG_SOUND, false] ?: false

    private const val APP_LANGUAGE = "app_language_code"
    private const val WHEEL_SOUND = "is_wheel_sound_enabled"
    private const val RPG_SOUND = "is_rps_sound_enabled"

}