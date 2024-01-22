package demo.sahha.android.domain.repo

import demo.sahha.android.domain.model.dto.SahhaAnalysisDto

interface AnalyzeRepo {
    suspend fun getAnalysis(): List<SahhaAnalysisDto>?
}