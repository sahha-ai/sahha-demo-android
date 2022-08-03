package demo.sahha.android.view.screens

import android.content.Context
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import demo.sahha.android.view.components.RowAndColumn
import demo.sahha.android.view.components.SahhaThemeButton
import demo.sahha.android.view.ui.theme.rubikFamily
import sdk.sahha.android.source.Sahha

private val verticalSpacer = Modifier.size(10.dp)

private const val AUTH_KEY = "sahha.demo.auth"
private const val TOKEN_KEY = "sahha.demo.auth.token"
private const val REFRESH_TOKEN_KEY = "sahha.demo.auth.refresh.token"

@Composable
fun Authenticate(navController: NavController, context: Context) {
    val sharedPrefs by lazy { context.getSharedPreferences(AUTH_KEY, Context.MODE_PRIVATE) }
    var token by remember { mutableStateOf(sharedPrefs.getString(TOKEN_KEY, "") ?: "") }
    var refreshToken by remember {
        mutableStateOf(
            sharedPrefs.getString(REFRESH_TOKEN_KEY, "") ?: ""
        )
    }
    var callback by remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

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
                    Text(text = "Authentication", fontFamily = rubikFamily)
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
        }
    ) {
        RowAndColumn {
            OutlinedTextField(
                value = token,
                onValueChange = {
                    token = it
                    sharedPrefs.edit().putString(TOKEN_KEY, it).apply()
                },
                label = {
                    Text(
                        "Token", fontFamily = rubikFamily,
                        fontSize = 14.sp
                    )
                },
                shape = RoundedCornerShape(25),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onNext = {
                    localFocusManager.moveFocus(FocusDirection.Down)
                })
            )
            Spacer(verticalSpacer)
            OutlinedTextField(
                value = refreshToken,
                onValueChange = {
                    refreshToken = it
                    sharedPrefs.edit().putString(REFRESH_TOKEN_KEY, it).apply()
                },
                label = {
                    Text(
                        "Refresh Token", fontFamily = rubikFamily,
                        fontSize = 14.sp
                    )
                },
                shape = RoundedCornerShape(25),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onDone = {
                    localFocusManager.clearFocus()
                })
            )
            Spacer(Modifier.size(20.dp))
            SahhaThemeButton(buttonTitle = "Authenticate", bottomSpace = 20.dp) {
                Sahha.authenticate(token, refreshToken) { error, success ->
                    if (success)
                        callback = "Stored successfully."
                    else
                        callback = error ?: "Failed to store tokens."
                }
            }
            Text(
                callback,
                fontFamily = rubikFamily,
                fontSize = 14.sp,
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            )
        }
    }
}