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
    private val authInteractor: AuthInteractor
) : ViewModel() {
    val isLoading = mutableStateOf(false)
    val url = mutableStateOf( "https://development-webview.netlify.app/insights")

    fun getToken(): String? {
        return authInteractor.getToken()
    }
}