package demo.sahha.android.data

import demo.sahha.android.domain.model.dto.SahhaAnalysisDto
import sdk.sahha.android.source.SahhaDemographic

object DemoCache {
    var demographic: SahhaDemographic? = null
    var analysis: List<SahhaAnalysisDto>? = null
}