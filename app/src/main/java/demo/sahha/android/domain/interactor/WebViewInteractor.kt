package demo.sahha.android.domain.interactor

import demo.sahha.android.domain.repo.WebViewRepo
import javax.inject.Inject

class WebViewInteractor @Inject constructor(
    private val repo: WebViewRepo
) {
    fun saveUrl(url: String) {
        repo.saveUrl(url)
    }

    fun getUrl(): String? {
        return repo.getUrl()
    }
}