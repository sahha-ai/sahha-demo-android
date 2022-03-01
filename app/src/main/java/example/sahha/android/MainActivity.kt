package example.sahha.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import example.sahha.android.ui.theme.SahhaexampleandroidTheme
import sdk.sahha.android.BackgroundController
import sdk.sahha.android.SahhaActivityRecognitionPermission

class MainActivity : ComponentActivity() {
    // Initial template code, yet to implement SDK.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SahhaexampleandroidTheme)
        setContent {
            SahhaexampleandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Greeting("Android")
                            Spacer(Modifier.size(16.dp))
                            Button(
                                onClick = {
                                    BackgroundController(this@MainActivity).startDataCollectionService()
                                }
                            ) {
                                Text("Service Test")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SahhaexampleandroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Greeting("Android")
                    Button(onClick = {}) {
                        Text("Permission Test")
                    }
                }
            }
        }
    }
}
