package demo.sahha.android.presentation.authenticate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.domain.interactor.AuthInteractor
import sdk.sahha.android.source.Sahha
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    private val interactor: AuthInteractor
) : ViewModel() {
    val isLoading = mutableStateOf(false)
    val appId = mutableStateOf("")
    val appSecret = mutableStateOf("")
    val externalId = mutableStateOf("")
    val callback = mutableStateOf("")

    init {
        displayLastUsedAuthData()
    }

    private fun displayLastUsedAuthData() {
        appId.value = interactor.getAppId()
        appSecret.value = interactor.getAppSecret()
        externalId.value = interactor.getExternalId()
    }

    fun authenticate() {
        isLoading.value = true
        Sahha.authenticate(
            appId.value,
            appSecret.value,
            externalId.value
        ) { error, success ->
            cacheAuthData()

            if (success)
                callback.value = "Stored successfully."
            else
                callback.value = error ?: "Failed to store tokens."

            isLoading.value = false
        }
    }
     fun cacheAuthData() {
         interactor.saveAuthData(
             appId = appId.value,
             appSecret = appSecret.value,
             externalId = externalId.value
         )
     }
}