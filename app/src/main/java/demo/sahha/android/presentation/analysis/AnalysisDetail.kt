package demo.sahha.android.presentation.analysis

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.components.SahhaLazyRowAndColumn
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import demo.sahha.android.presentation.ui.theme.rubikFamily

@Composable
fun AnalysisDetail(
    navController: NavController,
    viewModel: AnalysisDetailViewModel = hiltViewModel()
) {
    SahhaScaffoldWithTopbar(
        navController = navController,
        topBarTitle = viewModel.analysis.value?.type?.capitalize(Locale.current) ?: ""
    ) {
        SahhaLazyRowAndColumn {
            viewModel.analysis.value?.also { analysis ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = analysis.id, fontFamily = rubikFamily)
                }
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "State", fontFamily = rubikFamily)
                    Row { /* TODO: Colours based on state */
                        Text(
                            text = analysis.state.capitalize(Locale.current),
                            fontFamily = rubikFamily
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Score", fontFamily = rubikFamily)
                    Row { /* TODO: Colours based on state */
                        Text(text = analysis.score.toString(), fontFamily = rubikFamily)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                        Text(text = "Factors", fontFamily = rubikFamily)
                        analysis.factors.forEach { factor ->
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
                                Text(text = factor.name, fontFamily = rubikFamily)
                                Row {
                                    Text(text = factor.value, fontFamily = rubikFamily)
                                }
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                        Text(text = "Input Data", fontFamily = rubikFamily)
                        analysis.inputData.forEach { input ->
                            Text(text = input, fontFamily = rubikFamily, modifier = Modifier.padding(start = 10.dp))
                        }
                        Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Created At", fontFamily = rubikFamily)
                    Row { /* TODO: Colours based on state */
                        Text(text = viewModel.formatTime(analysis.createdAt))
                    }
                }
            }
        }
    }
}