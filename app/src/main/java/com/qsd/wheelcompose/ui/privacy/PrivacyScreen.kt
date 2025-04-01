package com.qsd.wheelcompose.ui.privacy

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import com.qsd.wheelcompose.utils.POLICY_URL

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PrivacyScreen() {
    var hasError by remember { mutableStateOf(false) }

    if (hasError) {
        ErrorScreen()
    } else {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun onReceivedError(
                            view: WebView,
                            errorCode: Int,
                            description: String?,
                            failingUrl: String?
                        ) {
                            hasError = true
                        }

                        override fun onReceivedError(
                            view: WebView,
                            request: android.webkit.WebResourceRequest,
                            error: WebResourceError
                        ) {
                            hasError = true
                        }
                    }
                    loadUrl(POLICY_URL)
                }
            },
            update = { it.loadUrl(POLICY_URL) }
        )
    }
}