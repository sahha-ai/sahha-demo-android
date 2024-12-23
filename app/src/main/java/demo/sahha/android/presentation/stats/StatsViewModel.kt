package demo.sahha.android.presentation.stats

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sdk.sahha.android.domain.model.local_logs.SahhaStat
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaSensor
import java.time.LocalDateTime
import javax.inject.Inject

class StatsViewModel @Inject constructor(

) : ViewModel() {
    var stats by mutableStateOf<List<SahhaStat>>(listOf())
        private set
    var error by mutableStateOf<String?>(null)
        private set

    fun setStats(context: Context, sahhaSensor: SahhaSensor) {
        error = null
        Sahha.getStats(
            sahhaSensor,
            Pair(LocalDateTime.now().minusDays(7), LocalDateTime.now())
        ) { error, sahhaStats ->
            error?.also { err ->
                viewModelScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, err, Toast.LENGTH_LONG).show()
                }
            }
            sahhaStats?.also {
                if (it.isEmpty()) {
                    this.error = "No stats found"
                }
                stats = it
            }
        }
    }
}