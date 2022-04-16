package demo.sahha.android.view.screens

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import demo.sahha.android.view.components.*
import demo.sahha.android.view.enum.Country
import demo.sahha.android.view.enum.Gender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import sdk.sahha.android.Sahha
import sdk.sahha.android.domain.model.profile.SahhaDemographic

@Composable
fun Profile(navController: NavController) {
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var birthCountry by remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current
    val mainScope = CoroutineScope(Main)

    Sahha.getDemographic { error, demographic ->
        error?.also {
            mainScope.launch {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

        demographic?.also {
            it.age?.let { _age -> age = _age.toString() }
            gender = it.gender ?: ""
            country = it.country ?: ""
            birthCountry = it.birthCountry ?: ""
        }
    }

    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Profile",
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }) {
        RowAndColumn {
            SahhaTextField(
                value = age,
                label = "Age",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions {
                    localFocusManager.clearFocus()
                },
                bottomSpacer = 20.dp
            ) {
                age = it
            }

            SahhaDropDown(label = "Gender", options = Gender.options) {
                gender = it
            }

            SahhaDropDown(label = "Country", options = Country.options) {
                country = it
            }

            SahhaDropDown(label = "Country of Birth", options = Country.options) {
                birthCountry = it
            }

            SahhaThemeButton(buttonTitle = "POST Profile") {
                Sahha.postDemographic(
                    SahhaDemographic(
                        age.toInt(), gender, country, birthCountry
                    )
                ) { error, success ->
                    error?.also {
                        mainScope.launch {
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        }
                    }

                    success?.also {
                        mainScope.launch {
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}