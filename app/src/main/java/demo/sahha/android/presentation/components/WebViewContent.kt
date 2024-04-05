package demo.sahha.android.presentation.components

import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate

@Composable
fun WebViewContent(
    httpHeader: Map<String, String>? = null,
    url: String,
    update: ((View) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
    ) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()

                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        databaseEnabled = true
                        cacheMode = WebSettings.LOAD_DEFAULT
                    }

                    httpHeader?.also { loadUrl(url, it) } ?: loadUrl(url)
                }
            },
            update = update ?: NoOpUpdate
        )
    }
}
