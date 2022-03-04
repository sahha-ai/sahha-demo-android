package example.sahha.android

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Authenticate: Screen(route = "authenticate")
    object Permission: Screen(route = "permission")
    object PostSleep: Screen(route = "post/sleep")
    object PostStep: Screen(route = "post/step")
    object PostLock: Screen(route = "post/lock")
    object PostDeviceActivity: Screen(route = "post/deviceActivity")
    object DeviceInfo: Screen(route = "deviceInfo")
}
