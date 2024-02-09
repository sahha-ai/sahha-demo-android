import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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

private val verticalSpacer = Modifier.size(10.dp)

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
                        Text(text = "WebView", fontFamily = rubikFamily)
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 10.dp,
                )
            },
        ) {
            WebViewModal(
                httpHeader = viewModel.getToken()?.let { token ->
                    mapOf(Pair("Authorization", "Profile $token"))
                }, url = viewModel.url.value
            )
        }
    }
}