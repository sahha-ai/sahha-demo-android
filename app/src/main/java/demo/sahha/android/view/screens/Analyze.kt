package demo.sahha.android.view.screens

import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import demo.sahha.android.view.components.RowAndColumn
import demo.sahha.android.view.components.SahhaScaffoldWithTopbar
import demo.sahha.android.view.components.SahhaThemeButton
import demo.sahha.android.view.ui.theme.rubikFamily
import sdk.sahha.android.Sahha

@Composable
fun Analyze(navController: NavController) {
    val context = LocalContext.current
    var analysisResponse by remember { mutableStateOf("") }
    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Analyze") {
        RowAndColumn {
            SahhaThemeButton(buttonTitle = "Analyze") {
                Sahha.analyze { error, success ->
                    error?.also {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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