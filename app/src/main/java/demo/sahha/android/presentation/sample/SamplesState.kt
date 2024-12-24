package demo.sahha.android.presentation.sample

import sdk.sahha.android.domain.model.local_logs.SahhaSample


data class SamplesState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val samples: Map<String, List<SahhaSample>> = emptyMap(),
)
