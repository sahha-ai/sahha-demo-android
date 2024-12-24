package demo.sahha.android.presentation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Authenticate: Screen(route = "authenticate")
    object Permission: Screen(route = "permission")
    object Analyze: Screen(route = "analyze")
    object Profile: Screen(route = "profile")
    object WebView: Screen(route = "web_view")
    object Stats: Screen(route = "stats")
    object Samples: Screen(route = "samples")
    object SamplesDetailScreen: Screen(route = "samples_detail")
}
