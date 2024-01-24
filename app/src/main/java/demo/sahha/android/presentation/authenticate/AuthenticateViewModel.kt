package demo.sahha.android.presentation.authenticate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.domain.interactor.AuthInteractor
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    private val interactor: AuthInteractor
) : ViewModel() {
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
     fun cacheAuthData() {
         interactor.saveAuthData(
             appId = appId.value,
             appSecret = appSecret.value,
             externalId = externalId.value
         )
     }
}