package demo.sahha.android.view.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import demo.sahha.android.view.components.RowAndColumn
import demo.sahha.android.view.components.SahhaScaffoldWithTopbar
import demo.sahha.android.view.components.SahhaThemeButton
import demo.sahha.android.view.ui.theme.rubikFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaSensor

@Composable
fun DeviceActivity(
    navController: NavController
) {
    val context = LocalContext.current
    val mainScope = CoroutineScope(Main)

    var deviceData by remember { mutableStateOf(listOf<String>()) }

    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Device Activity") {
        RowAndColumn {

            SahhaThemeButton(buttonTitle = "Manually POST Data") {
                Sahha.postSensorData() { error, success ->
                    if(success)
                        mainScope.launch {
                            Toast.makeText(context, "Post successful.", Toast.LENGTH_LONG).show()
                        }
                    else
                        mainScope.launch {
                            Toast.makeText(context, error ?: "Failed to post data.", Toast.LENGTH_LONG).show()
                        }
                }
            }

            Text("Device", fontFamily = rubikFamily)
            Spacer(Modifier.size(10.dp))

            LazyColumn {
                item {
                    for (data in deviceData) {
                        Text(
                            data,
                            fontFamily = rubikFamily
                        )
                        Spacer(Modifier.size(10.dp))
                    }
                }
            }
        }
    }
}