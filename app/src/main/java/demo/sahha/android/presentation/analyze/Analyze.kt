package demo.sahha.android.presentation.screens

import LoadingCircle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
                LoadingCircle()
            } else {
                SahhaLazyRowAndColumn(columnPadding = 0.dp) {
                    LazyColumn(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillParentMaxSize()
                    ) {
                        viewModel.analysisResponse.value?.also {
                            items(it.sortedByDescending { it.scoreDateTime }) { analysis ->
                                val formattedTime = viewModel.formatTime(analysis.scoreDateTime)
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