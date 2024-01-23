package demo.sahha.android.domain.interactor

import demo.sahha.android.domain.model.dto.SahhaAnalysisDto
import demo.sahha.android.domain.repo.AnalyzeRepo
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class AnalyzeInteractor @Inject constructor(
    private val analyzeRepo: AnalyzeRepo
) {
    suspend fun getAnalysis(id: String): SahhaAnalysisDto? {
        return analyzeRepo.getAnalysis(id)
    }
    suspend fun getInferences(): List<SahhaAnalysisDto>? {
        return analyzeRepo.getInferences()
    }

    fun formatTime(isoTime: String): String {
        val formatterPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZZZZ"
        val dateTimeFormatter = DateTimeFormatter.ofPattern(formatterPattern, Locale.US)
        val formatted = ZonedDateTime.parse(isoTime, dateTimeFormatter).withFixedOffsetZone()

        return "${formatted.toLocalDate()}"
    }
}