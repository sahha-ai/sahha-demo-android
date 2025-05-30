package demo.sahha.android.presentation.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import demo.sahha.android.R
import demo.sahha.android.presentation.Screen
import demo.sahha.android.presentation.components.SahhaThemeButton

@Composable
fun Home(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                item {
                    Image(
                        painterResource(id = R.drawable.ic_sahha),
                        contentDescription = "Sahha Logo"
                    )
                    Spacer(Modifier.size(100.dp))

                    SahhaThemeButton(buttonTitle = "Authenticate") {
                        navController.navigate(Screen.Authenticate.route)
                    }

                    SahhaThemeButton(buttonTitle = "Permissions") {
                        navController.navigate(Screen.Permission.route)
                    }

                    SahhaThemeButton(buttonTitle = "Profile") {
                        navController.navigate(Screen.Profile.route)
                    }

                    SahhaThemeButton(buttonTitle = "Scores") {
                        navController.navigate(Screen.WebView.route)
                    }

                    SahhaThemeButton(buttonTitle = "Stats") {
                        navController.navigate(Screen.Stats.route)
                    }

                    SahhaThemeButton(buttonTitle = "Samples") {
                        navController.navigate(Screen.Samples.route)
                    }

                    SahhaThemeButton(buttonTitle = "Smart Watch") {
                        navController.navigate(Screen.SmartWatch.route)
                    }
                }
            }
        }
    }
}