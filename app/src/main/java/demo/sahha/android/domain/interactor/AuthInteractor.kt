package demo.sahha.android.domain.interactor

import demo.sahha.android.domain.repo.AuthRepo
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepo: AuthRepo
) {
    fun saveAuthData(
        appId: String,
        appSecret: String,
        externalId: String
    ) {
        authRepo.saveAuthData(appId, appSecret, externalId)
    }

    fun getAppId(): String {
        return authRepo.getAppId() ?: ""
    }

    fun getAppSecret(): String {
        return authRepo.getAppSecret() ?: ""
    }

    fun getExternalId(): String {
        return authRepo.getExternalId() ?: ""
    }

    fun getToken(): String? {
        return authRepo.getToken()
    }

    fun saveToken(token: String) {
        authRepo.saveToken(token)
    }
}