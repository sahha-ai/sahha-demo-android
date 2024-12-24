package demo.sahha.android.presentation.stats

import sdk.sahha.android.domain.model.local_logs.SahhaStat


data class StatsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val stats: List<SahhaStat> = emptyList(),
)
