import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.data.DemoCache
import demo.sahha.android.presentation.components.RowAndColumn
import demo.sahha.android.presentation.components.SahhaThemeButton
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
            RowAndColumn {
                OutlinedTextField(
                    value = viewModel.url.value,
                    onValueChange = {
                        viewModel.url.value = it
                    },
                    label = {
                        Text(
                            "URL", fontFamily = rubikFamily,
                            fontSize = 14.sp
                        )
                    },
                    shape = RoundedCornerShape(25),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Uri
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        localFocusManager.clearFocus()
                    }),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(text = "Add profile token", fontFamily = rubikFamily)
                    Checkbox(
                        checked = viewModel.authHeaderChecked.value,
                        onCheckedChange = { checked ->
                            viewModel.authHeaderChecked.value = checked
                            DemoCache.addTokenChecked = checked
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colors.primary,
                            uncheckedColor = MaterialTheme.colors.primary,
                            checkmarkColor = MaterialTheme.colors.onPrimary
                        )
                    )
                }
                AnimatedVisibility(visible = viewModel.authHeaderChecked.value) {
                    OutlinedTextField(
                        value = viewModel.authHeaderText.value,
                        onValueChange = {
                            viewModel.authHeaderText.value = it
                            viewModel.cacheToken(it)
                        },
                        label = {
                            Text(
                                "Authorization Header", fontFamily = rubikFamily,
                                fontSize = 14.sp
                            )
                        },
                        shape = RoundedCornerShape(25),
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            localFocusManager.clearFocus()
                        }),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.onBackground,
                            focusedLabelColor = MaterialTheme.colors.onBackground
                        ),
                    )
                }
                Spacer(verticalSpacer)

                SahhaThemeButton(buttonTitle = "Launch", bottomSpace = 20.dp) {
                    viewModel.cacheUrl()
                    viewModel.showWebView.value = true
                }
            }

            if (viewModel.showWebView.value)
                WebViewModal(
                    httpHeader = viewModel.getToken()?.let { token ->
                        mapOf(
                            Pair(
                                "Authorization",
                                "Profile ${token}"
                            )
                        )
                    }, url = viewModel.url.value
                ) {
                    viewModel.showWebView.value = false
                }
        }
    }
}