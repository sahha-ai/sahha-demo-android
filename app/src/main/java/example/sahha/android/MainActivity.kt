package example.sahha.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import example.sahha.android.ui.theme.SahhaexampleandroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SahhaexampleandroidTheme)

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
                        Authenticate()
                    }
                    composable(Screen.Permission.route) {
                        Permission()
                    }
                    composable(Screen.PostSleep.route) {
                        PostSleep()
                    }
                    composable(Screen.PostStep.route) {
                        PostStep()
                    }
                    composable(Screen.PostLock.route) {
                        PostLock()
                    }
                    composable(Screen.PostDeviceActivity.route) {
                        PostDeviceActivity()
                    }
                    composable(Screen.DeviceInfo.route) {
                        DeviceInfo()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SahhaexampleandroidTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // TODO
                }
            }
        }
    }
}
