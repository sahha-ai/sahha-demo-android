package demo.sahha.android.presentation.sample

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.Screen
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import demo.sahha.android.presentation.sample.components.SensorSampleItem
import demo.sahha.android.presentation.stats.components.CalendarWrapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun SamplesScreen(
    navController: NavController,
    viewModel: SamplesViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val alpha by animateFloatAsState(
        targetValue = if (state.isLoading) 0.5f else 1.0f,
        animationSpec = tween(durationMillis = 250),
        label = "Alpha animation"
    )

    SahhaScaffoldWithTopbar(navController, "Samples") {
        Column {
            CalendarWrapper { year, month, dayOfMonth ->
                viewModel.displaySamples(
                    LocalDateTime.of(
                        LocalDate.of(year, month, dayOfMonth),
                        LocalTime.MIDNIGHT
                    ),
                    LocalDateTime.of(
                        LocalDate.of(year, month, dayOfMonth).plusDays(1),
                        LocalTime.MIDNIGHT
                    )
                )
            }
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .alpha(alpha)
            ) {
                if (state.error != null) {
                    Text(state.error)
                } else {
                    LazyColumn {
                        val grouped = state.samples
                        items(grouped.keys.toList()) { sensorType ->
                            grouped[sensorType]?.also {
                                SensorSampleItem(
                                    sensorType
                                ) {
                                    navController.navigate(
                                        "${Screen.SamplesDetailScreen.route}/${sensorType}"
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}