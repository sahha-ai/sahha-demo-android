package demo.sahha.android.presentation.webview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.data.DemoCache
import demo.sahha.android.domain.interactor.AuthInteractor
import demo.sahha.android.domain.interactor.WebViewInteractor
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    private val webViewInteractor: WebViewInteractor,
    private val authInteractor: AuthInteractor
) : ViewModel() {
    val isLoading = mutableStateOf(false)
    val url = mutableStateOf(webViewInteractor.getUrl() ?: "https://development-webview.netlify.app/insights")
    val showWebView = mutableStateOf(false)
    val authHeaderChecked = mutableStateOf(false)
    val authHeaderText = mutableStateOf("Profile ")

    init {
        authHeaderChecked.value = DemoCache.addTokenChecked
        authHeaderText.value = authInteractor.getToken() ?: "Profile "
    }

    fun cacheUrl() {
        webViewInteractor.saveUrl(url.value)
    }

    fun getToken(): String? {
        return authInteractor.getToken()
    }

    fun cacheToken(token: String) {
        authInteractor.saveToken(token)
    }
}