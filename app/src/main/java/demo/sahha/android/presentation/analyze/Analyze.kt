package demo.sahha.android.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.Screen
import demo.sahha.android.presentation.analyze.AnalyzeViewModel
import demo.sahha.android.presentation.analyze.components.AnalyzeItem
import demo.sahha.android.presentation.components.SahhaLazyRowAndColumn
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import demo.sahha.android.presentation.components.SahhaThemeButton

@Composable
fun Analyze(
    navController: NavController,
    viewModel: AnalyzeViewModel = hiltViewModel()
) {
    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Analyze") {
        Column(modifier = Modifier.padding(20.dp)) {
            SahhaThemeButton(buttonTitle = "Analyze") {
                viewModel.analyze()
            }
            if (viewModel.isLoading.value) {
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
                SahhaLazyRowAndColumn(columnPadding = 0.dp) {
                    LazyColumn(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillParentMaxSize()
                    ) {
                        viewModel.analysisResponse.value?.also {
                            items(it.sortedByDescending { it.createdAt }) { analysis ->
                                val formattedTime = viewModel.formatTime(analysis.createdAt)
                                AnalyzeItem(
                                    analysis, formattedTime
                                ) {
                                    navController.navigate("${Screen.Analyze.route}/${analysis.id}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}