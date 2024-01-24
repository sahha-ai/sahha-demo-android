package demo.sahha.android.domain.repo

interface WebViewRepo {
     fun saveUrl(url: String)
     fun getUrl(): String?
}