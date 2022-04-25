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
fun MotionActivity(
    navController: NavController
) {
    val context = LocalContext.current
    val mainScope = CoroutineScope(Main)
    var sleepData by remember { mutableStateOf(listOf<String>()) }

    SahhaScaffoldWithTopbar(
        navController = navController,
        topBarTitle = "Motion Activity"
    ) {
        RowAndColumn {
            Sahha.motion.getData { sleepData = it }

            SahhaThemeButton(
                buttonTitle = "Manually POST Data",
                bottomSpace = 20.dp
            ) {
                Sahha.postSensorData(setOf(SahhaSensor.sleep)) { error, success ->
                    if (success)
                        mainScope.launch {
                            Toast.makeText(context, "Post successful.", Toast.LENGTH_LONG).show()
                            Sahha.motion.getData { sleepData = it }
                        }
                    else
                        mainScope.launch {
                            Toast.makeText(
                                context,
                                error ?: "Failed to post data.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                }
            }
            Text("Sleep", fontFamily = rubikFamily)
            Spacer(Modifier.size(10.dp))

            LazyColumn {
                item {
                    for (sleep in sleepData) {
                        Text(
                            sleep,
                            fontFamily = rubikFamily
                        )
                        Spacer(Modifier.size(10.dp))
                    }
                }
            }
        }
    }
}