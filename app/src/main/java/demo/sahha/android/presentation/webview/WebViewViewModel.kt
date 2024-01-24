package demo.sahha.android.presentation.webview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.domain.interactor.AuthInteractor
import demo.sahha.android.domain.interactor.WebViewInteractor
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    private val webViewInteractor: WebViewInteractor,
    private val authInteractor: AuthInteractor
) : ViewModel() {
    var isLoading = mutableStateOf(false)
    var url = mutableStateOf(webViewInteractor.getUrl() ?: "https://docs.sahha.ai")
    var showWebView = mutableStateOf(false)

    fun cacheUrl() {
        webViewInteractor.saveUrl(url.value)
    }

    fun getToken(): String {
        return authInteractor.getToken()
    }
}