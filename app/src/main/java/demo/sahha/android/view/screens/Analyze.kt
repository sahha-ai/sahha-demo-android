package demo.sahha.android.view.screens

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import demo.sahha.android.view.components.RowAndColumn
import demo.sahha.android.view.components.SahhaLazyRow
import demo.sahha.android.view.components.SahhaScaffoldWithTopbar
import demo.sahha.android.view.components.SahhaThemeButton
import demo.sahha.android.view.ui.theme.rubikFamily
import sdk.sahha.android.source.Sahha

@Composable
fun Analyze(navController: NavController) {
    var analysisResponse by remember { mutableStateOf("") }
    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Analyze") {
        RowAndColumn {
            SahhaThemeButton(buttonTitle = "Analyze") {
                Sahha.analyze { error, success ->
                    error?.also {
                        analysisResponse = it
                    }
                    success?.also {
                        analysisResponse = it
                    }
                }
            }
            Text(analysisResponse, fontFamily = rubikFamily)
        }
    }
}