package demo.sahha.android.presentation.stats

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import demo.sahha.android.presentation.stats.components.CalendarWrapper
import demo.sahha.android.presentation.stats.components.StatDataRow
import java.time.LocalDateTime

@Composable
fun StatsScreen(
    navController: NavController,
    viewModel: StatsViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val alpha by animateFloatAsState(
        targetValue = if (state.isLoading) 0.5f else 1.0f,
        animationSpec = tween(durationMillis = 250),
        label = "Alpha animation"
    )

    SahhaScaffoldWithTopbar(navController, "Stats") {
        Column {
            CalendarWrapper { year, month, dayOfMonth ->
                viewModel.displayStats(
                    LocalDateTime.of(year, month, dayOfMonth, 0, 0, 0)
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
                        items(state.stats) { statData ->
                            StatDataRow(statData)
                        }
                    }
                }
            }

        }
    }

}