package demo.sahha.android.presentation.analyze

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.data.DemoCache
import demo.sahha.android.domain.interactor.AnalyzeInteractor
import demo.sahha.android.domain.model.dto.SahhaAnalysisDto
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalyzeViewModel @Inject constructor(
    private val interactor: AnalyzeInteractor
) : ViewModel() {
    val analysisResponse: MutableState<List<SahhaAnalysisDto>?> = mutableStateOf(null)
    val isLoading = mutableStateOf(false)

    init {
        analysisResponse.value = DemoCache.analysis
    }

    fun formatTime(isoTime: String): String {
        return interactor.formatTime(isoTime)
    }

    fun analyze() {
        viewModelScope.launch {
            isLoading.value = true
            analysisResponse.value = interactor.getInferences()
            isLoading.value = false
        }
    }
}