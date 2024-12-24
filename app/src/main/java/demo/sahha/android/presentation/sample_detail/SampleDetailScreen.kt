package demo.sahha.android.presentation.sample_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import demo.sahha.android.presentation.sample.SamplesViewModel

@Composable
fun SampleDetailScreen(
    sensorType: String,
    navController: NavController,
    viewModel: SamplesViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val typeSamples = state.samples[sensorType]

    SahhaScaffoldWithTopbar(
        navController,
        sensorType
    ) {
        if (state.isLoading) Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
        else typeSamples?.also { samples ->
            LazyColumn(
                modifier = Modifier.padding(20.dp)
            ) {
                items(samples) { sampleData ->
                    Text("id: ${sampleData.id}")
                    Text("type: ${sampleData.type}")
                    Text("value: ${sampleData.value}")
                    Text("unit: ${sampleData.unit}")
                    Text("startDateTime: ${sampleData.startDateTime}")
                    Text("endDateTime: ${sampleData.endDateTime}")
                    Text("source: ${sampleData.source}")
                    Divider(Modifier.padding(vertical = 10.dp))
                }
            }
        }
            ?: Text("No samples were found: Please check that permissions are enabled and there is data saved in Health Connect!")
    }
}