package demo.sahha.android.presentation.components

import androidx.compose.runtime.Composable

@Composable
fun WebViewModal(
    httpHeader: Map<String, String>? = null,
    url: String,
) {
    WebViewContent(
        httpHeader = httpHeader,
        url = url,
    )
}