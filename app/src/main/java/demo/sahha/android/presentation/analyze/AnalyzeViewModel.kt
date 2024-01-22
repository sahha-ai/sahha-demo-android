package demo.sahha.android.presentation.analyze

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AnalyzeViewModel @Inject constructor(

): ViewModel() {
    var analysisResponse = mutableStateOf("")
    var isLoading = mutableStateOf(false)
}