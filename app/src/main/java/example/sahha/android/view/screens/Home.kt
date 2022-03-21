package example.sahha.android.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import example.sahha.android.view.ui.theme.rubikFamily

@Composable
fun Home(navController: NavController) {
    val verticalSpacer = Modifier.size(10.dp)

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
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
                    },
                    shape = RoundedCornerShape(25)
                ) {
                    Text("Authenticate", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.Permission.route)
                    },
                    shape = RoundedCornerShape(25)
                ) {
                    Text("Permissions", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.PostSleep.route)
                    },
                    shape = RoundedCornerShape(25)
                ) {
                    Text("POST Sleep Data", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.PostStep.route)
                    },
                    shape = RoundedCornerShape(25)
                ) {
                    Text("POST Step Data", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.PostLock.route)
                    },
                    shape = RoundedCornerShape(25)
                ) {
                    Text("POST Lock Data", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.PostDeviceActivity.route)
                    },
                    shape = RoundedCornerShape(25)
                ) {
                    Text("POST Device Activity Data", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Screen.DeviceInfo.route)
                    },
                    shape = RoundedCornerShape(25)
                ) {
                    Text("Track Device Info", fontFamily = rubikFamily)
                }
            }
        }
    }
}