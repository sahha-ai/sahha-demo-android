package demo.sahha.android.presentation.analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import javax.inject.Inject

@Composable
fun Analysis(
    navController: NavController,
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "" ) {

    }
}