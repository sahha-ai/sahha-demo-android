import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.components.WebViewModal
import demo.sahha.android.presentation.ui.theme.rubikFamily
import demo.sahha.android.presentation.webview.WebViewViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WebView(
    navController: NavController,
    viewModel: WebViewViewModel = hiltViewModel()
) {
    val localFocusManager = LocalFocusManager.current

    if (viewModel.isLoading.value) {
        LoadingCircle()
    } else {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                },
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "My Health", fontFamily = rubikFamily)
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.url.value = "https://development.webview.sahha.ai/app"
                            },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(text = "Scores", fontFamily = rubikFamily)
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 10.dp,
                )
            },
        ) {
            val httpHeader = viewModel.getToken()?.let { token ->
                mapOf(Pair("Authorization", "Profile $token"))
            }
            WebViewModal(
                httpHeader = httpHeader, url = viewModel.url.value
            ) { view ->
                val webView = (view as WebView)
                httpHeader?.also { header ->
                    webView.loadUrl(viewModel.url.value, header)
                } ?: webView.loadUrl(viewModel.url.value)
            }
        }
    }
}