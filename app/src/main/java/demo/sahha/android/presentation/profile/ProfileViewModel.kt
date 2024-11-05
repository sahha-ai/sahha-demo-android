package demo.sahha.android.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.domain.interactor.ProfileInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sdk.sahha.android.source.SahhaDemographic
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @Named("mainScope") private val mainScope: CoroutineScope,
    private val interactor: ProfileInteractor
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
        viewModelScope.launch {
            val demographic = interactor.getDemographic()

            if (demographic == null) {
                isLoading.value = false
                return@launch
            }

            interactor.cacheDemographic(demographic)

            age.value = demographic.age?.toString() ?: ""
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
            birthDate.value = demographic.birthDate?.substringBefore('T') ?: ""
            isLoading.value = false
        }
    }

    fun postDemographic(
        context: Context,
    ) {
        val demographic = SahhaDemographic(
            age.value.ifEmpty { null }?.toInt(),
            gender.value.takeIf { it != "Please select" },
            country.value.ifEmpty { null },
            birthCountry.value.ifEmpty { null },
            ethnicity.value.ifEmpty { null },
            occupation.value.ifEmpty { null },
            industry.value.ifEmpty { null },
            incomeRange.value.ifEmpty { null },
            education.value.ifEmpty { null },
            relationship.value.ifEmpty { null },
            locale.value.ifEmpty { null },
            livingArrangement.value.ifEmpty { null },
            birthDate.value.ifEmpty { null }
        )

        mainScope.launch {
            val result = interactor.postDemographic(demographic)
            Toast.makeText(
                context,
                if (result.second) "Successfully sent" else result.first, Toast.LENGTH_LONG
            ).show()
        }
    }
}