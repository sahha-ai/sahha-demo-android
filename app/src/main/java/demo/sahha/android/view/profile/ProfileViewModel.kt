package demo.sahha.android.view.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sdk.sahha.android.source.Sahha
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @Named("mainScope") val mainScope: CoroutineScope
) : ViewModel() {
    var isLoading = mutableStateOf(false)

    val age = mutableStateOf("")
    val gender = mutableStateOf("Please select")
    val country = mutableStateOf("")
    val birthCountry = mutableStateOf("")
    val ethnicity = mutableStateOf("")
    val occupation = mutableStateOf("")
    val industry = mutableStateOf("")
    val incomeRange = mutableStateOf("")
    val education = mutableStateOf("")
    val relationship = mutableStateOf("")
    val locale = mutableStateOf("")
    val livingArrangement = mutableStateOf("")
    val birthDate = mutableStateOf("")

    init {
        isLoading.value = true
        Sahha.getDemographic { _, demographic ->
            demographic?.also {
                viewModelScope.launch(Dispatchers.IO) {
                    age.value = it.age.toString()
                    gender.value = it.gender ?: "Please select"
                    country.value = it.country ?: ""
                    birthCountry.value = it.birthCountry ?: ""
                    ethnicity.value = it.ethnicity ?: ""
                    occupation.value = it.occupation ?: ""
                    industry.value = it.industry ?: ""
                    incomeRange.value = it.incomeRange ?: ""
                    education.value = it.education ?: ""
                    relationship.value = it.relationship ?: ""
                    locale.value = it.locale ?: ""
                    livingArrangement.value = it.livingArrangement ?: ""
                    birthDate.value = it.birthDate ?: ""

                    isLoading.value = false
                }
            }
        }
    }
}