package demo.sahha.android.data.repo

import android.content.SharedPreferences
import demo.sahha.android.domain.repo.WebViewRepo
import javax.inject.Inject

private const val url_cache = "url_cache"
class WebViewRepoImpl @Inject constructor(
    private val savedPreferences: SharedPreferences
): WebViewRepo {
    override fun saveUrl(url: String) {
        savedPreferences.edit().putString(url_cache, url).apply()
    }

    override fun getUrl(): String? {
        return savedPreferences.getString(url_cache, null)
    }
}