package demo.sahha.android.view.screens.authenticate

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(

) : ViewModel() {
    private val _state = mutableStateOf(AuthenticateState())
    val state: State<AuthenticateState> = _state

    fun setProfileToken(token: String) {
        _state.value.profileToken = token
    }
}