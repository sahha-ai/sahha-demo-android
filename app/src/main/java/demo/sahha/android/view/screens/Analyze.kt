package demo.sahha.android.view.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import demo.sahha.android.view.components.SahhaLazyRowAndColumn
import demo.sahha.android.view.components.SahhaScaffoldWithTopbar
import demo.sahha.android.view.components.SahhaThemeButton
import demo.sahha.android.view.ui.theme.rubikFamily
import sdk.sahha.android.source.Sahha
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Analyze(navController: NavController) {
    var analysisResponse by remember { mutableStateOf("") }
    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Analyze") {
        SahhaLazyRowAndColumn {
            SahhaThemeButton(buttonTitle = "Analyze") {
                Sahha.analyze(
                    dates = Pair(
                        LocalDateTime.now().minusDays(8),
                        LocalDateTime.now()
                    )
                ) { error, success ->
                    error?.also {
                        analysisResponse = it
                    }
                    success?.also {
                        analysisResponse = it
                    }
                }
            }
            Text(
                analysisResponse,
                fontFamily = rubikFamily,
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                )
            )
        }
    }
}