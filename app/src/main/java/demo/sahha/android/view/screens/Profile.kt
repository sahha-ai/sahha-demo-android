package demo.sahha.android.view.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import demo.sahha.android.view.components.*
import demo.sahha.android.view.enum.Gender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaDemographic

@Composable
fun Profile(navController: NavController) {
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Please select") }
    var country by remember { mutableStateOf("") }
    var birthCountry by remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current
    val mainScope = CoroutineScope(Main)
    val countryCharLimit = 2


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

            SahhaDropDown(
                label = "Gender",
                options = Gender.options,
                existingOption = gender
            ) {
                gender = it
            }

            SahhaTextField(
                value = country,
                label = "Country",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= countryCharLimit) {
                    country = it.letters().uppercase()
                }
            }

            SahhaTextField(
                value = birthCountry,
                label = "Country of Birth",
                keyboardActions = KeyboardActions {
                    localFocusManager.clearFocus()
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            ) {
                if (it.length <= countryCharLimit) {
                    birthCountry = it.letters().uppercase()
                }
            }


//            SahhaDropDown(
//                label = "Country",
//                options = Country.options,
//                existingOption = country
//            ) {
//                country = it
//            }
//
//            SahhaDropDown(
//                label = "Country of Birth",
//                options = Country.options,
//                existingOption = birthCountry
//            ) {
//                birthCountry = it
//            }

            SahhaThemeButton(buttonTitle = "POST Profile") {
                Sahha.postDemographic(
                    SahhaDemographic(
                        age.toInt(), gender, country, birthCountry
                    )
                ) { error, success ->
                    mainScope.launch {
                        Toast.makeText(context, error ?: "Successful", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    Sahha.getDemographic { error, demographic ->
        Log.w("Profile", demographic.toString())
        demographic?.also {
            it.age?.also { a -> age = a.toString() }
            gender = it.gender ?: "Please select"
            country = it.country ?: ""
            birthCountry = it.birthCountry ?: ""
        }
    }
}

private fun String.letters() = filter { it.isLetter() }

