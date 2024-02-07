package demo.sahha.android.presentation.screens

import LoadingCircle
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.presentation.authenticate.AuthenticateViewModel
import demo.sahha.android.presentation.components.RowAndColumn
import demo.sahha.android.presentation.components.SahhaThemeButton
import demo.sahha.android.presentation.ui.theme.rubikFamily
import sdk.sahha.android.source.Sahha

private val verticalSpacer = Modifier.size(10.dp)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Authenticate(navController: NavController, viewModel: AuthenticateViewModel = hiltViewModel()) {
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
        },
    ) {
        RowAndColumn {
            OutlinedTextField(
                value = viewModel.appId.value,
                onValueChange = { viewModel.appId.value = it },
                label = {
                    Text(
                        "App ID", fontFamily = rubikFamily,
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
                value = viewModel.appSecret.value,
                onValueChange = { viewModel.appSecret.value = it },
                label = {
                    Text(
                        "App Secret", fontFamily = rubikFamily,
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
            Spacer(verticalSpacer)
            OutlinedTextField(
                value = viewModel.externalId.value,
                onValueChange = { viewModel.externalId.value = it },
                label = {
                    Text(
                        "External ID", fontFamily = rubikFamily,
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
                viewModel.authenticate()
            }
            if(viewModel.isLoading.value) {
                LoadingCircle()
            } else {
                Text(
                    viewModel.callback.value,
                    fontFamily = rubikFamily,
                    fontSize = 14.sp,
                    modifier = Modifier.verticalScroll(
                        rememberScrollState()
                    )
                )
            }
        }
    }
}