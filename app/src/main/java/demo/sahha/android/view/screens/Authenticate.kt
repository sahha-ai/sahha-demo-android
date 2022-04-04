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
import sdk.sahha.android.Sahha

private val verticalSpacer = Modifier.size(10.dp)

const val CUST_ID = "a78afc9b-cae3-4736-8bb1-ca174c16a2ed"
const val PROFILE_ID = "5b992635-ade9-401c-a3bc-733b8651851d"

@Composable
fun Authenticate(navController: NavController, context: Context) {
    var customerId by remember { mutableStateOf(CUST_ID) }
    var profileId by remember { mutableStateOf(PROFILE_ID) }
    var token by remember { mutableStateOf("") }
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
                value = customerId,
                onValueChange = { customerId = it },
                label = {
                    Text(
                        "Customer ID", fontFamily = rubikFamily,
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
                value = profileId,
                onValueChange = { profileId = it },
                label = {
                    Text(
                        "Profile ID", fontFamily = rubikFamily,
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
                Sahha.authenticate(
                    customerId,
                    profileId,
                ) { value ->
                    token = value
                }
            }
            Text(
                "Token: $token",
                fontFamily = rubikFamily,
                fontSize = 14.sp,
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            )
        }
    }
}