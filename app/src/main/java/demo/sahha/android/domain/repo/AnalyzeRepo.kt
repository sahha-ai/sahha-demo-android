package demo.sahha.android.domain.repo

import demo.sahha.android.domain.model.dto.SahhaAnalysisDto

interface AnalyzeRepo {
    suspend fun getInferences(): List<SahhaAnalysisDto>?
    suspend fun getAnalysis(id: String): SahhaAnalysisDto?
}