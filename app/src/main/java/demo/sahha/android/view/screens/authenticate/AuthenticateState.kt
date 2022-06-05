package demo.sahha.android.view.screens.authenticate

data class AuthenticateState(
    var profileToken: String = "",
    var refreshToken: String = "",
    var callback: String = ""
)
