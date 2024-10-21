package demo.sahha.android.presentation.profile

import LoadingCircle
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import demo.sahha.android.enum.Gender
import demo.sahha.android.presentation.components.SahhaDropDown
import demo.sahha.android.presentation.components.SahhaLazyRowAndColumn
import demo.sahha.android.presentation.components.SahhaScaffoldWithTopbar
import demo.sahha.android.presentation.components.SahhaTextField
import demo.sahha.android.presentation.components.SahhaThemeButton

private const val countryCharLimit = 2
private const val standardCharLimit = 50
private const val dateCharLimit = 10

@Composable
fun Profile(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current

    SahhaScaffoldWithTopbar(navController = navController, topBarTitle = "Profile",
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }) {
        if (viewModel.isLoading.value)
            LoadingCircle()
        else SahhaLazyRowAndColumn {
            SahhaTextField(
                value = viewModel.age.value,
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
                viewModel.age.value = it
            }

            SahhaDropDown(
                label = "Gender",
                options = Gender.options,
                existingOption = viewModel.gender.value
            ) {
                viewModel.gender.value = it
            }

            SahhaTextField(
                value = viewModel.country.value,
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
                if (it.length > countryCharLimit) return@SahhaTextField
                viewModel.country.value = it.letters().uppercase()
            }

            SahhaTextField(
                value = viewModel.birthCountry.value,
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
                if (it.length > countryCharLimit) return@SahhaTextField
                viewModel.birthCountry.value = it.letters().uppercase()
            }

            SahhaTextField(
                value = viewModel.ethnicity.value,
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
                if (it.length > standardCharLimit) return@SahhaTextField
                viewModel.ethnicity.value = it
            }

            SahhaTextField(
                value = viewModel.occupation.value,
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
                if (it.length > standardCharLimit) return@SahhaTextField
                viewModel.occupation.value = it
            }

            SahhaTextField(
                value = viewModel.industry.value,
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
                if (it.length > standardCharLimit) return@SahhaTextField
                viewModel.industry.value = it
            }

            SahhaTextField(
                value = viewModel.incomeRange.value,
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
                if (it.length > standardCharLimit) return@SahhaTextField
                viewModel.incomeRange.value = it
            }

            SahhaTextField(
                value = viewModel.education.value,
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
                if (it.length > standardCharLimit) return@SahhaTextField
                viewModel.education.value = it
            }

            SahhaTextField(
                value = viewModel.relationship.value,
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
                if (it.length > standardCharLimit) return@SahhaTextField
                viewModel.relationship.value = it
            }

            SahhaTextField(
                value = viewModel.locale.value,
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
                if (it.length > standardCharLimit) return@SahhaTextField
                viewModel.locale.value = it
            }

            SahhaTextField(
                value = viewModel.livingArrangement.value,
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
                if (it.length > standardCharLimit) return@SahhaTextField
                viewModel.livingArrangement.value = it
            }

            SahhaTextField(
                value = viewModel.birthDate.value,
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
                if (it.length > dateCharLimit) return@SahhaTextField
                viewModel.birthDate.value = it.date()
            }

            SahhaThemeButton(buttonTitle = "POST Profile") {
                viewModel.postDemographic(context)
            }
        }
    }
}

private fun String.letters() = filter { it.isLetter() }
private fun String.date() = filter { it.isDigit() or (it == '-') }


