package demo.sahha.android.presentation.components

import android.view.View
import androidx.compose.runtime.Composable

@Composable
fun WebViewModal(
    httpHeader: Map<String, String>? = null,
    url: String,
    update: ((View) -> Unit)? = null
) {
    WebViewContent(
        httpHeader = httpHeader,
        url = url,
        update = update
    )
}