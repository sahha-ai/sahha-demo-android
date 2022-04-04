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
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import sdk.sahha.android.Sahha
import sdk.sahha.android.domain.model.enums.SahhaSensor

@Composable
fun MotionActivity(
    navController: NavController
) {
    val context = LocalContext.current
    val mainScope = CoroutineScope(Main)
    val ioScope = CoroutineScope(IO)
    var sleepData by remember { mutableStateOf(listOf<String>()) }

    SahhaScaffoldWithTopbar(
        navController = navController,
        topBarTitle = "Motion Activity"
    ) {
        RowAndColumn {
            Sahha.getSleepData { sleepData = it }

            SahhaThemeButton(
                buttonTitle = "Manually POST Data",
                bottomSpace = 20.dp
            ) {
                Sahha.motion.postData(SahhaSensor.SLEEP) { error, success ->
                    error?.also {
                        mainScope.launch {
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                            Sahha.getSleepData { sleepData = it }
                        }
                    }
                    success?.also {
                        mainScope.launch {
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                            Sahha.getSleepData { sleepData = it }
                        }
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