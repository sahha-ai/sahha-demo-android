package demo.sahha.android.view.screens

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Authenticate: Screen(route = "authenticate")
    object Permission: Screen(route = "permission")
    object MotionActivity: Screen(route = "motionActivity")
}
