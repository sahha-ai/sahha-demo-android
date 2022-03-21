package example.sahha.android.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import example.sahha.android.view.ui.theme.rubikFamily
import sdk.sahha.android.Sahha

private val verticalSpacer = Modifier.size(10.dp)

@Composable
fun Permission(
    navController: NavController
) {
    var activityRecognitionStatus by remember { mutableStateOf("pending") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Permissions", fontFamily = rubikFamily)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp,
            )
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Spacer(verticalSpacer)
                Text("Permission Status", fontFamily = rubikFamily, fontSize = 20.sp)
                Spacer(verticalSpacer)
                Text("Activity Recognition: $activityRecognitionStatus", fontFamily = rubikFamily)
                Spacer(Modifier.size(20.dp))
                Button(
                    onClick = {
                        Sahha.activate { newStatus ->
                            activityRecognitionStatus = newStatus.name
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Activity Recognition", fontFamily = rubikFamily)
                }
                Spacer(verticalSpacer)
                Text("Or", fontFamily = rubikFamily)
                Spacer(verticalSpacer)
                Button(
                    onClick = {
                        Sahha.promptUserToActivate { newStatus ->
                            activityRecognitionStatus = newStatus.name
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open Settings", fontFamily = rubikFamily)
                }
            }
        }
    }
}