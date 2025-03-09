package com.example.wheelcompose.base

import WheelComposeTheme
import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.wheelcompose.model.data.SnackBarDataHolder
import com.example.wheelcompose.model.data.local.prefs.AppPrefs
import com.example.wheelcompose.ui.widget.CommonSnackBar
import java.util.Locale


abstract class BaseActivity<ViewModel : BaseViewModel> : ComponentActivity() {
    abstract val viewModel: ViewModel
    private val snackBarDataState = mutableStateOf<SnackBarDataHolder?>(null)

    @Composable
    abstract fun BuiContent(savedInstanceState: Bundle?, scaffoldState: SnackbarHostState)
    abstract fun init(savedInstanceState: Bundle?)

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
            ViewCompat.onApplyWindowInsets(view, windowInsets)
        }
        enableEdgeToEdge()
        init(savedInstanceState)
        setContent {
            WheelComposeTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { _ ->
                    snackBarDataState.value?.let { data ->
                        CommonSnackBar(
                            snackbarHostState = snackbarHostState,
                            message = data.message,
                            actionLabel = data.actionLabel,
                            duration = data.duration,
                            onAction = {
                                data.onAction?.invoke()
                                snackBarDataState.value = null
                            },
                            onDismiss = {
                                snackBarDataState.value = null
                            }
                        )
                    }
                    BuiContent(savedInstanceState, snackbarHostState)
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        val context = newBase?.createConfigurationContext(newBase.resources.configuration.apply {
            setLocale(Locale(AppPrefs.language))
        })
        super.attachBaseContext(ContextWrapper(context))
    }


    fun showSnackBar(snackBarData: SnackBarDataHolder) {
        snackBarDataState.value = snackBarData
    }
}
