package demo.sahha.android.presentation.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import sdk.sahha.android.domain.model.local_logs.SahhaStat
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaSensor
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class StatsViewModel @Inject constructor(

) : ViewModel() {
    var state by mutableStateOf(StatsState())
        private set

    init {
        displayStats(LocalDateTime.now())
    }

    private suspend fun getStats(
        date: LocalDateTime,
        sensor: SahhaSensor
    ): Pair<String?, List<SahhaStat>?> = suspendCancellableCoroutine { cont ->
        try {
            Sahha.getStats(
                sensor,
                Pair(date, date)
            ) { error, stats ->
                if (cont.isActive) cont.resume(Pair(error, stats))
            }
        } catch (e: Exception) {
            if (cont.isActive) cont.resume(Pair(e.message, null))
        }
    }

    fun displayStats(date: LocalDateTime) {
        state = state.copy(isLoading = true)
        println(date)

        val sahhaStats: MutableList<SahhaStat> = mutableListOf()
        viewModelScope.launch {
            SahhaSensor.values().forEach { sensor ->
                val result = getStats(date, sensor)
                result.first?.also { err ->
                    sahhaStats += SahhaStat(
                        id = "",
                        category = "",
                        type = sensor.name,
                        value = 0.0,
                        unit = "",
                        startDateTime = ZonedDateTime.now(),
                        endDateTime = ZonedDateTime.now(),
                    )
                } ?: result.second?.also { stat ->
                    sahhaStats += stat
                }
            }

            val filtered = sahhaStats.filter { it.id != "" }
            state = if (filtered.isEmpty()) state.copy(
                isLoading = false,
                stats = sahhaStats,
                error = "No stats were found: Please check that permissions are enabled and there is data saved in Health Connect!"
            )
            else state.copy(isLoading = false, stats = filtered, error = null)
        }
    }
}