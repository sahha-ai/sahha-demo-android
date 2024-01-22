package demo.sahha.android.view.authenticate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.domain.interaction.AuthInteractor
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : ViewModel() {
    val appId = mutableStateOf("")
    val appSecret = mutableStateOf("")
    val externalId = mutableStateOf("")
    val callback = mutableStateOf("")

    init {
        displayLastUsedAuthData()
    }

    private fun displayLastUsedAuthData() {
        appId.value = authInteractor.getAppId()
        appSecret.value = authInteractor.getAppSecret()
        externalId.value = authInteractor.getExternalId()
    }
     fun cacheAuthData() {
         authInteractor.saveAuthData(
             appId = appId.value,
             appSecret = appSecret.value,
             externalId = externalId.value
         )
     }
}