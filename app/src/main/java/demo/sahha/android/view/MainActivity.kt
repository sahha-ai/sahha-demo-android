package demo.sahha.android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import demo.sahha.android.R
import demo.sahha.android.view.screens.*
import demo.sahha.android.view.ui.theme.SahhaexampleandroidTheme
import sdk.sahha.android.Sahha
import sdk.sahha.android.domain.model.config.SahhaSettings
import sdk.sahha.android.domain.model.enums.SahhaEnvironment

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SahhaexampleandroidTheme)

        Sahha.configure(
            this,
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
                        Authenticate(navController, this@MainActivity)
                    }
                    composable(Screen.Permission.route) {
                        Permission(navController)
                    }
                    composable(Screen.MotionActivity.route) {
                        MotionActivity(navController)
                    }
                    composable(Screen.DeviceActivity.route) {
                        DeviceActivity(navController)
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
