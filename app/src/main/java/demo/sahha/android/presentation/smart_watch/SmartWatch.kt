package demo.sahha.android.presentation.smart_watch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import demo.sahha.android.presentation.components.SahhaThemeButton

@Composable
fun SmartWatch(
    navController: NavController,
    viewModel: SmartWatchViewModel = hiltViewModel()
) {
    val state = viewModel.state

    SahhaScaffoldWithTopbar(navController, "Smart Watch") {
        if (state.isLoading)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        else if (state.exerciseEnded)
            ExerciseSummary(viewModel)
        else CurrentExercise(viewModel)
    }

}

@Composable
fun CurrentExercise(viewModel: SmartWatchViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Current Exercise")
        Divider(modifier = Modifier.padding(vertical = 10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Heart rate:")
            Text("${state.heartRate} bpm")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Duration:")
            Text("${state.currentDuration} seconds")
        }
    }
}

@Composable
fun ExerciseSummary(viewModel: SmartWatchViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("${state.exerciseName} summary")
        Divider(modifier = Modifier.padding(vertical = 10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Heart rate minimum:")
            Text("${state.min} bpm")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Heart rate maximum:")
            Text("${state.max} bpm")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Heart rate average:")
            Text("${state.average.format("%.3f")} bpm")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Exercise started:")
            Text(state.start)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Exercise ended:")
            Text(state.end)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Exercise duration:")
            Text("${state.totalDuration} seconds")
        }
        Spacer(modifier = Modifier.size(20.dp))
        SahhaThemeButton(buttonTitle = "Another Exercise") {
            viewModel.setExerciseEnded(false)
        }
    }
}