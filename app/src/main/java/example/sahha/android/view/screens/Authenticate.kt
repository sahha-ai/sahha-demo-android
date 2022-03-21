package example.sahha.android.view.screens

import android.content.Context
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import example.sahha.android.view.ui.theme.rubikFamily
import sdk.sahha.android.Sahha

private val verticalSpacer = Modifier.size(10.dp)

@Composable
fun Authenticate(navController: NavController, context: Context) {
    var customerId by remember { mutableStateOf("") }
    var profileId by remember { mutableStateOf("") }
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Spacer(verticalSpacer)
                Text("Credentials", fontFamily = rubikFamily, fontSize = 16.sp)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
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
                    Button(
                        onClick = {
                            Sahha.authenticate(
                                customerId,
                                profileId,
                            ) { value ->
                                token = value
                            }
                        },
                        shape = RoundedCornerShape(25),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Authenticate", fontFamily = rubikFamily, fontSize = 14.sp)
                    }
                    Spacer(Modifier.size(20.dp))
                    Text(token, fontFamily = rubikFamily, fontSize = 14.sp)
                }
            }
        }
    }
}