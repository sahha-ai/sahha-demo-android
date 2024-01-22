package demo.sahha.android.presentation.screens

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
import demo.sahha.android.presentation.components.RowAndColumn
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import demo.sahha.android.presentation.components.SahhaThemeButton
import demo.sahha.android.presentation.ui.theme.rubikFamily
import sdk.sahha.android.source.Sahha

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
        RowAndColumn(
            columnHorizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(verticalSpacer)
            Text("Permission Status", fontFamily = rubikFamily, fontSize = 20.sp)
            Spacer(verticalSpacer)
            Text(activityRecognitionStatus, fontFamily = rubikFamily)
            Spacer(Modifier.size(20.dp))
            SahhaThemeButton(buttonTitle = "Enable Permissions") {
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