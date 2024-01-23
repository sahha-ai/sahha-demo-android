package demo.sahha.android.presentation.webview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.domain.interactor.WebViewInteractor
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    private val interactor: WebViewInteractor
) : ViewModel() {
    var isLoading = mutableStateOf(false)
    var url = mutableStateOf(interactor.getUrl() ?: "https://docs.sahha.ai")
    var showWebView = mutableStateOf(false)

    fun cacheUrl() {
        interactor.saveUrl(url.value)
    }
}