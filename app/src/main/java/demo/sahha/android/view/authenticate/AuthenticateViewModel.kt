package demo.sahha.android.view.authenticate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor() : ViewModel() {
    val appId = mutableStateOf("")
    val appSecret = mutableStateOf("")
    val externalId = mutableStateOf("")
    val callback = mutableStateOf("")
}