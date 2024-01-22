package demo.sahha.android.domain.interactor

import demo.sahha.android.domain.model.dto.SahhaAnalysisDto
import demo.sahha.android.domain.repo.AnalyzeRepo
import javax.inject.Inject

class AnalyzeInteractor @Inject constructor(
    private val analyzeRepo: AnalyzeRepo
) {
    suspend fun getInferences(): List<SahhaAnalysisDto>? {
        return analyzeRepo.getAnalysis()
    }
}