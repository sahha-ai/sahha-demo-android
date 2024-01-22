package demo.sahha.android.presentation.analyze

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import demo.sahha.android.domain.interactor.AnalyzeInteractor
import demo.sahha.android.domain.model.dto.SahhaAnalysisDto
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnalyzeViewModel @Inject constructor(
    private val interactor: AnalyzeInteractor
) : ViewModel() {
    var analysisResponse: MutableState<List<SahhaAnalysisDto>?> = mutableStateOf(null)
    var isLoading = mutableStateOf(false)

    fun analyze() {
        viewModelScope.launch {
            isLoading.value = true
            analysisResponse.value = interactor.getInferences()
            isLoading.value = false
        }
    }
}