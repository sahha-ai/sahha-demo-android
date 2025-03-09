package demo.sahha.android.presentation

import WebView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import demo.sahha.android.R
import demo.sahha.android.presentation.analysis.AnalysisDetail
import demo.sahha.android.presentation.profile.Profile
import demo.sahha.android.presentation.sample.SamplesScreen
import demo.sahha.android.presentation.sample_detail.SampleDetailScreen
import demo.sahha.android.presentation.screens.Analyze
import demo.sahha.android.presentation.screens.Authenticate
import demo.sahha.android.presentation.screens.Home
import demo.sahha.android.presentation.screens.Permission
import demo.sahha.android.presentation.smart_watch.SmartWatch
import demo.sahha.android.presentation.stats.StatsScreen
import demo.sahha.android.presentation.ui.theme.SahhaexampleandroidTheme
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaEnvironment
import sdk.sahha.android.source.SahhaSettings

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SahhaexampleandroidTheme)

        Sahha.configure(
            this,
            SahhaSettings(
                environment = SahhaEnvironment.sandbox,
            )
        )

        setContent {
            SahhaexampleandroidTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.background(color = MaterialTheme.colors.background)
                ) {
                    composable(Screen.Home.route) {
                        Home(navController)
                    }
                    composable(Screen.Authenticate.route) {
                        Authenticate(navController)
                    }
                    composable(Screen.Permission.route) {
                        Permission(navController)
                    }
                    composable(Screen.Analyze.route) {
                        Analyze(navController)
                    }
                    composable(Screen.Analyze.route + "/{id}") {
                        AnalysisDetail(navController)
                    }
                    composable(Screen.Profile.route) {
                        Profile(navController)
                    }
                    composable(Screen.WebView.route) {
                        WebView(navController)
                    }
                    composable(Screen.Stats.route) {
                        StatsScreen(navController)
                    }
                    composable(Screen.Samples.route) {
                        SamplesScreen(navController)
                    }
                    composable("${Screen.SamplesDetailScreen.route}/{sensorType}") { backStackEntry ->
                        val sensorType = backStackEntry.arguments?.getString("sensorType")
                        sensorType?.also { type ->
                            SampleDetailScreen(
                                type,
                                navController
                            )
                        }
                    }
                    composable(Screen.SmartWatch.route) {
                        SmartWatch(navController)
                    }
                }
            }
        }
    }
}
