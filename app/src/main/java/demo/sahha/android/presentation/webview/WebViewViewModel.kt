package demo.sahha.android.presentation.webview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(

): ViewModel() {
    var isLoading = mutableStateOf(false)
    var url = mutableStateOf("https://docs.sahha.ai")
    var showWebView = mutableStateOf(false)
}