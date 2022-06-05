package demo.sahha.android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import demo.sahha.android.R
import demo.sahha.android.view.screens.*
import demo.sahha.android.view.screens.authenticate.AuthenticateViewModel
import demo.sahha.android.view.ui.theme.SahhaexampleandroidTheme
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaEnvironment
import sdk.sahha.android.source.SahhaSettings

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SahhaexampleandroidTheme)

        Sahha.configure(
            application,
            SahhaSettings(environment = SahhaEnvironment.development)
        )

        setContent {
            SahhaexampleandroidTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        Home(navController)
                    }
                    composable(Screen.Authenticate.route) {
                        Authenticate(navController)
                    }
                    composable(Screen.Permission.route) {
                        Permission(navController)
                    }
                    composable(Screen.Analyze.route) {
                        Analyze(navController)
                    }
                    composable(Screen.Profile.route) {
                        Profile(navController)
                    }
                }
            }
        }
    }
}
