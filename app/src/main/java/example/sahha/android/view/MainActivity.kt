package example.sahha.android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import example.sahha.android.R
import example.sahha.android.view.screens.*
import example.sahha.android.view.ui.theme.SahhaexampleandroidTheme
import sdk.sahha.android.Sahha

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SahhaexampleandroidTheme)

        Sahha.configure(this)

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
                    composable(Screen.PostSleep.route) {
                        PostSleep(navController)
                    }
                    composable(Screen.PostStep.route) {
                        PostStep(navController)
                    }
                    composable(Screen.PostLock.route) {
                        PostLock(navController)
                    }
                    composable(Screen.PostDeviceActivity.route) {
                        PostDeviceActivity(navController)
                    }
                    composable(Screen.DeviceInfo.route) {
                        DeviceInfo(navController)
                    }
                }
            }
        }
    }
}
