package demo.sahha.android.data.repo

import android.content.SharedPreferences
import demo.sahha.android.BuildConfig
import demo.sahha.android.domain.repo.AuthRepo
import sdk.sahha.android.source.Sahha
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

private const val app_id: String = "app_id"
private const val app_secret: String = "app_secret"
private const val external_id: String = "external_id"
private const val profile_token: String = "profile_token"

class AuthRepoImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthRepo {
    override fun saveAuthData(
        appId: String,
        appSecret: String,
        externalId: String
    ) {
        sharedPreferences.edit()
            .putString(app_id, appId)
            .putString(app_secret, appSecret)
            .putString(external_id, externalId)
            .apply()
    }

    override fun clearAuthData() {
        sharedPreferences.edit().clear().apply()
    }

    override fun getAppId(): String? {
        return sharedPreferences.getString(app_id, null)
    }

    override fun getAppSecret(): String? {
        return sharedPreferences.getString(app_secret, null)
    }

    override fun getExternalId(): String? {
        return sharedPreferences.getString(external_id, null)
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(profile_token, token).apply()
    }

    override fun getToken(): String? {
         return sharedPreferences.getString(profile_token, null)
    }
}