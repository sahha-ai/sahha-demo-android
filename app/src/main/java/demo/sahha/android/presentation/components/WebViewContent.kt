package demo.sahha.android.presentation.components

import android.media.Image
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import demo.sahha.android.R
import demo.sahha.android.presentation.ui.theme.rubikFamily

@Composable
fun WebViewContent(
    httpHeader: Map<String, String>? = null,
    url: String,
    title: String? = null,
    icon: @Composable (() -> Unit)? = null,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.background(color = MaterialTheme.colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.invoke()
            title?.let {
                Text(
                    text = it,
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onBackground
                )
            }
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .zIndex(2f)
                    .padding(5.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    httpHeader?.also { loadUrl(url, it) } ?: loadUrl(url)
                }
            },
        )
    }
}