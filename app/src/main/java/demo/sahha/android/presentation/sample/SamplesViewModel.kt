package demo.sahha.android.presentation.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import sdk.sahha.android.domain.model.local_logs.SahhaSample
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaSensor
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class SamplesViewModel @Inject constructor(

) : ViewModel() {
    var state by mutableStateOf(SamplesState())
        private set

    init {
        displaySamples(
            LocalDateTime.of(
                LocalDate.now(),
                LocalTime.MIDNIGHT
            ),
            LocalDateTime.of(
                LocalDate.now().plusDays(1),
                LocalTime.MIDNIGHT
            )
        )
    }

    private suspend fun getSamples(
        start: LocalDateTime,
        end: LocalDateTime,
        sensor: SahhaSensor
    ): Pair<String?, List<SahhaSample>?> = suspendCancellableCoroutine { cont ->
        try {
            Sahha.getSamples(
                sensor,
                Pair(start, end)
            ) { error, samples ->
                if (cont.isActive) cont.resume(Pair(error, samples))
            }
        } catch (e: Exception) {
            if (cont.isActive) cont.resume(Pair(e.message, null))
        }
    }

    fun displaySamples(
        start: LocalDateTime,
        end: LocalDateTime
    ) {
        state = state.copy(isLoading = true)

        val sahhaSamples: MutableList<SahhaSample> = mutableListOf()
        viewModelScope.launch {
            SahhaSensor.values().forEach { sensor ->
                val result = getSamples(start, end, sensor)
                result.second?.also { sample ->
                    sahhaSamples += sample
                }
            }

            state = if (sahhaSamples.isEmpty()) state.copy(
                isLoading = false,
                error = "No samples were found: Please check that permissions are enabled and there is data saved in Health Connect!"
            )
            else state.copy(isLoading = false, samples = sahhaSamples.groupBy { it.type }, error = null)
        }
    }
}