package demo.sahha.android.view.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import demo.sahha.android.view.components.RowAndColumn
import demo.sahha.android.view.components.SahhaScaffoldWithTopbar
import demo.sahha.android.view.components.SahhaThemeButton
import demo.sahha.android.view.ui.theme.rubikFamily
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaSensor

private val verticalSpacer = Modifier.size(10.dp)

@Composable
fun Permission(
    navController: NavController
) {
    var activityRecognitionStatus by remember { mutableStateOf("") }
    val context = LocalContext.current

    Sahha.getSensorStatus(context) { _, newStatus ->
        activityRecognitionStatus = newStatus.name
    }

    SahhaScaffoldWithTopbar(
        navController = navController,
        topBarTitle = "Permissions"
    )
    {
        RowAndColumn (
            columnHorizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(verticalSpacer)
            Text("Permission Status", fontFamily = rubikFamily, fontSize = 20.sp)
            Spacer(verticalSpacer)
            Text("Activity Recognition: $activityRecognitionStatus", fontFamily = rubikFamily)
            Spacer(Modifier.size(20.dp))
            SahhaThemeButton(buttonTitle = "Activity Recognition") {
                Sahha.enableSensors(context) { _, newStatus ->
                    activityRecognitionStatus = newStatus.name
                }
            }
            Text("Or", fontFamily = rubikFamily)
            Spacer(verticalSpacer)
            SahhaThemeButton(buttonTitle = "Open Settings") {
                Sahha.openAppSettings(context)
            }
        }
    }
}