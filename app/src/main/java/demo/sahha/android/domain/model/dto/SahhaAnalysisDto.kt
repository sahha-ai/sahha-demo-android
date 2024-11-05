package demo.sahha.android.domain.model.dto


data class SahhaAnalysisDto(
    val id: String = "",
    val type: String = "",
    val state: String = "",
    val score: Double = 0.0,
    val factors: List<SahhaFactorDto> = listOf(),
    val dataSources: List<String> = listOf(),
    val scoreDateTime: String = ""
)
