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
fun Profile(
    navController: NavController,
) {
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Please select") }
    var country by remember { mutableStateOf("") }
    var birthCountry by remember { mutableStateOf("") }
    var ethnicity by remember { mutableStateOf("") }
    var occupation by remember { mutableStateOf("") }
    var industry by remember { mutableStateOf("") }
    var incomeRange by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var relationship by remember { mutableStateOf("") }
    var locale by remember { mutableStateOf("") }
    var livingArrangement by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current
    val mainScope = CoroutineScope(Main)
    val countryCharLimit = 2
    val standardCharLimit = 50


    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Profile",
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }) {
        SahhaLazyRowAndColumn {
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
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= countryCharLimit) {
                    birthCountry = it.letters().uppercase()
                }
            }

            SahhaTextField(
                value = ethnicity,
                label = "Ethnicity",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= standardCharLimit)
                    ethnicity = it
            }

            SahhaTextField(
                value = occupation,
                label = "Occupation",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= standardCharLimit)
                    occupation = it
            }

            SahhaTextField(
                value = industry,
                label = "Industry",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= standardCharLimit)
                    industry = it
            }

            SahhaTextField(
                value = incomeRange,
                label = "Income",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= standardCharLimit)
                    incomeRange = it
            }

            SahhaTextField(
                value = education,
                label = "Education",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= standardCharLimit)
                    education = it
            }

            SahhaTextField(
                value = relationship,
                label = "Relationship",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= standardCharLimit)
                    relationship = it
            }

            SahhaTextField(
                value = locale,
                label = "Locale",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= standardCharLimit)
                    locale = it
            }

            SahhaTextField(
                value = livingArrangement,
                label = "Living Arrangement",
                keyboardActions = KeyboardActions {
                    localFocusManager.moveFocus(FocusDirection.Down)
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ) {
                if (it.length <= standardCharLimit)
                    livingArrangement = it
            }

            SahhaTextField(
                value = birthDate,
                label = "Birth Date (YYYY-MM-DD)",
                keyboardActions = KeyboardActions {
                    localFocusManager.clearFocus()
                },
                bottomSpacer = 20.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            ) {
                if (it.length <= standardCharLimit)
                    birthDate = it.date()
            }



            SahhaThemeButton(buttonTitle = "POST Profile") {
                Sahha.postDemographic(
                    SahhaDemographic(
                        age.ifEmpty { null }?.toInt(),
                        gender.takeIf { it != "Please select" },
                        country.ifEmpty { null },
                        birthCountry.ifEmpty { null },
                        ethnicity.ifEmpty { null },
                        occupation.ifEmpty { null },
                        industry.ifEmpty { null },
                        incomeRange.ifEmpty { null },
                        education.ifEmpty { null },
                        relationship.ifEmpty { null },
                        locale.ifEmpty { null },
                        livingArrangement.ifEmpty { null },
                        birthDate.ifEmpty { null }
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
            ethnicity = it.ethnicity ?: ""
            occupation = it.occupation ?: ""
            industry = it.industry ?: ""
            incomeRange = it.incomeRange ?: ""
            education = it.education ?: ""
            relationship = it.relationship ?: ""
            locale = it.locale ?: ""
            livingArrangement = it.livingArrangement ?: ""
            birthDate = it.birthDate?.substring(0, 10) ?: ""
        }
    }
}

private fun String.letters() = filter { it.isLetter() }
private fun String.date() = filter { it.isDigit() or (it == '-') }


