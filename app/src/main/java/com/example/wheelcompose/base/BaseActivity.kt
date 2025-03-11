package com.example.wheelcompose.base

import AppTheme
import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.wheelcompose.model.data.SnackBarDataHolder
import com.example.wheelcompose.model.data.local.prefs.AppPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale


abstract class BaseActivity<ViewModel : BaseViewModel> : ComponentActivity() {
    abstract val viewModel: ViewModel
    private val _snackBarDataState = MutableStateFlow<SnackBarDataHolder?>(null)
    open val snackBarDataState: StateFlow<SnackBarDataHolder?> = _snackBarDataState

    @Composable
    abstract fun BuiContent(
        savedInstanceState: Bundle?,
        snackBarHostState: SnackbarHostState,
    )

    abstract fun init(savedInstanceState: Bundle?)
    abstract val titleTopBar: String

    @OptIn(ExperimentalMaterial3Api::class)
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
            AppTheme {
                val snackBarHostState = remember { SnackbarHostState() }
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(text = titleTopBar)
                            }
                        )
                    }
                ) { innerPaddingValues ->
                    Surface(modifier = Modifier.padding(innerPaddingValues)) {
                        BuiContent(savedInstanceState, snackBarHostState)
                    }
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


    fun updateSnackBarState(snackBarData: SnackBarDataHolder) {
        _snackBarDataState.update { snackBarData }
    }
}
