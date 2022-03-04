package example.sahha.android

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import example.sahha.android.ui.theme.rubikFamily

@Composable
fun Home(navController: NavController) {
    val verticalSpacer = Modifier.size(10.dp)

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
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.Authenticate.route)
                    }
                ) {
                    Text("Authenticate", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.Permission.route)
                    }
                ) {
                    Text("Activity Recognition Permission", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.PostSleep.route)
                    }
                ) {
                    Text("POST Sleep Data", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.PostStep.route)
                    }
                ) {
                    Text("POST Step Data", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.PostLock.route)
                    }
                ) {
                    Text("POST Lock Data", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.PostDeviceActivity.route)
                    }
                ) {
                    Text("POST Device Activity Data", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.DeviceInfo.route)
                    }
                ) {
                    Text("Track Device Info", fontFamily = rubikFamily)
                }
            }
        }
    }
}