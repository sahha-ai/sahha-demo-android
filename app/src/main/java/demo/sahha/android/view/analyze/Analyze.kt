package demo.sahha.android.view.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.view.analyze.AnalyzeViewModel
import demo.sahha.android.view.components.SahhaLazyRowAndColumn
import demo.sahha.android.view.components.SahhaScaffoldWithTopbar
import demo.sahha.android.view.components.SahhaThemeButton
import demo.sahha.android.view.ui.theme.rubikFamily
import sdk.sahha.android.source.Sahha
import java.time.LocalDateTime

@Composable
fun Analyze(
    navController: NavController,
    viewModel: AnalyzeViewModel = hiltViewModel()
) {
    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Analyze") {
        Column(modifier = Modifier.padding(20.dp)) {
            SahhaThemeButton(buttonTitle = "Analyze") {
                viewModel.isLoading.value = true
                Sahha.analyze(
                    dates = Pair(
                        LocalDateTime.now().minusDays(7),
                        LocalDateTime.now()
                    )
                ) { error, success ->
                    viewModel.isLoading.value = false

                    error?.also {
                        viewModel.analysisResponse.value = it
                    }
                    success?.also {
                        viewModel.analysisResponse.value = it
                    }
                }
            }
            if(viewModel.isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 4.dp,
                        color = MaterialTheme.colors.primary
                    )
                }
            } else {
                SahhaLazyRowAndColumn {
                    Text(
                        viewModel.analysisResponse.value,
                        fontFamily = rubikFamily,
                        modifier = Modifier.horizontalScroll(
                            rememberScrollState()
                        )
                    )
                }
            }
        }
    }
}