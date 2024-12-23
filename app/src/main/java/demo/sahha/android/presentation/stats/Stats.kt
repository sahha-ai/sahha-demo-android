package demo.sahha.android.presentation.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.components.SahhaDropDown
import demo.sahha.android.presentation.components.SahhaLazyRowAndColumnList
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import sdk.sahha.android.source.SahhaSensor

@Composable
fun Stats(
    navController: NavController,
    viewModel: StatsViewModel = hiltViewModel()
) {
    SahhaScaffoldWithTopbar(
        navController = navController,
        topBarTitle = "Stats"
    ) {
        StatsView(viewModel)
    }
}

@Composable
fun StatsView(
    viewModel: StatsViewModel
) {
    val context = LocalContext.current

    Column {
        SahhaDropDown(
            label = "Sensor",
            options = SahhaSensor.values().map { it.name },
            modifier = Modifier.padding(20.dp)
        ) { selected ->
            val sensor = SahhaSensor.valueOf(selected)
            viewModel.setStats(context, sensor)
        }

        viewModel.error?.also { err ->
            Text(
                err,
                modifier = Modifier.padding(20.dp)
            )
        } ?: SahhaLazyRowAndColumnList(
            list = viewModel.stats
        ) { stat ->
            Text(stat.id)
            Text(stat.type)
            Text(stat.value.toString())
            Text(stat.unit)
            Text(stat.startDateTime.toString())
            Text(stat.endDateTime.toString())
            Text(stat.sources.toString())
            Divider(Modifier.padding(10.dp))
        }
    }
}

@Preview
@Composable
fun StatsPreview() {
    StatsView(StatsViewModel())
}