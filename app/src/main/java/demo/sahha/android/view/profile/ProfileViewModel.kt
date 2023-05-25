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
            if(demographic == null) {
                isLoading.value = false
                return@getDemographic
            }
                viewModelScope.launch {
                    age.value = demographic.age.toString()
                    gender.value = demographic.gender ?: "Please select"
                    country.value = demographic.country ?: ""
                    birthCountry.value = demographic.birthCountry ?: ""
                    ethnicity.value = demographic.ethnicity ?: ""
                    occupation.value = demographic.occupation ?: ""
                    industry.value = demographic.industry ?: ""
                    incomeRange.value = demographic.incomeRange ?: ""
                    education.value = demographic.education ?: ""
                    relationship.value = demographic.relationship ?: ""
                    locale.value = demographic.locale ?: ""
                    livingArrangement.value = demographic.livingArrangement ?: ""
                    birthDate.value = demographic.birthDate ?: ""
                    isLoading.value = false
                }
        }
    }
}