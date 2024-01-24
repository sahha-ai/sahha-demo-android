package demo.sahha.android.presentation.analysis

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.sahha.android.domain.interactor.AnalyzeInteractor
import demo.sahha.android.domain.model.dto.SahhaAnalysisDto
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisDetailViewModel @Inject constructor(
    private val interactor: AnalyzeInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val analysis: MutableState<SahhaAnalysisDto?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>("id")?.let { id ->
                analysis.value = interactor.getAnalysis(id)
            }
        }
    }

    fun formatTime(time: String): String {
        return interactor.formatTime(time)
    }
}