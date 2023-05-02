package demo.sahha.android.view.profile

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import demo.sahha.android.view.components.SahhaDropDown
import demo.sahha.android.view.components.SahhaLazyRowAndColumn
import demo.sahha.android.view.components.SahhaScaffoldWithTopbar
import demo.sahha.android.view.components.SahhaTextField
import demo.sahha.android.view.components.SahhaThemeButton
import kotlinx.coroutines.launch
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaDemographic

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

    if (viewModel.isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                strokeWidth = 4.dp,
                color = MaterialTheme.colors.primary
            )
        }
    } else {
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
                    Sahha.postDemographic(
                        SahhaDemographic(
                            viewModel.age.value.ifEmpty { null }?.toInt(),
                            viewModel.gender.value.takeIf { it != "Please select" },
                            viewModel.country.value.ifEmpty { null },
                            viewModel.birthCountry.value.ifEmpty { null },
                            viewModel.ethnicity.value.ifEmpty { null },
                            viewModel.occupation.value.ifEmpty { null },
                            viewModel.industry.value.ifEmpty { null },
                            viewModel.incomeRange.value.ifEmpty { null },
                            viewModel.education.value.ifEmpty { null },
                            viewModel.relationship.value.ifEmpty { null },
                            viewModel.locale.value.ifEmpty { null },
                            viewModel.livingArrangement.value.ifEmpty { null },
                            viewModel.birthDate.value.ifEmpty { null }
                        )
                    ) { error, success ->
                        viewModel.mainScope.launch {
                            Toast.makeText(
                                context,
                                if (success) "Successful" else error, Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

}

private fun String.letters() = filter { it.isLetter() }
private fun String.date() = filter { it.isDigit() or (it == '-') }


