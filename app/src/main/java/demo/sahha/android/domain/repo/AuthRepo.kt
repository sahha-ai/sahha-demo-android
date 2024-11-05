package demo.sahha.android.domain.repo

interface AuthRepo {
    fun saveAuthData(
        appId: String,
        appSecret: String,
        externalId: String
    )
    fun clearAuthData()
    fun getAppId(): String?
    fun getAppSecret(): String?
    fun getExternalId(): String?
    fun getToken(): String?
    fun saveToken(token: String)
}