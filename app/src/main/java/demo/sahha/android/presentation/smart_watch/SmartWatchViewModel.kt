package demo.sahha.android.presentation.smart_watch

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SmartWatchViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val messageClient = Wearable.getMessageClient(context)
    var state by mutableStateOf(SmartWatchState())
        private set

    private val messageListener =
        MessageClient.OnMessageReceivedListener { event ->
            when (event.path) {
                "/name" -> {
                    state = state.copy(exerciseName = event.data.decodeToString())
                }

                "/heart" -> {
                    state = state.copy(heartRate = event.data.decodeToString())
                }

                "/duration" -> {
                    state = state.copy(currentDuration = event.data.decodeToString())
                }

                "/heart/min" -> {
                    state = state.copy(min = event.data.decodeToString())
                }

                "/heart/max" -> {
                    state = state.copy(max = event.data.decodeToString())
                }

                "/heart/average" -> {
                    state = state.copy(average = event.data.decodeToString())
                }

                "/duration/start" -> {
                    val start = event.data.decodeToString()
                    val startLdt =
                        ZonedDateTime
                            .parse(start)
                            .toLocalDateTime()
                            .atZone(ZoneOffset.UTC)
                            .withZoneSameInstant(ZoneId.systemDefault())
                    state = state.copy(start = startLdt.format(DateTimeFormatter.ISO_LOCAL_TIME))
                }

                "/duration/end" -> {
                    val end = event.data.decodeToString()
                    val endLdt = ZonedDateTime
                        .parse(end)
                        .toLocalDateTime()
                        .atZone(ZoneOffset.UTC)
                        .withZoneSameInstant(ZoneId.systemDefault())
                    state = state.copy(end = endLdt.format(DateTimeFormatter.ISO_LOCAL_TIME))
                }

                "/duration/total" -> {
                    state = state.copy(totalDuration = event.data.decodeToString())
                }

                "/ended" -> {
                    setExerciseEnded(true)
                }
            }
        }

    fun setExerciseEnded(ended: Boolean) {
        if (ended) messageClient.removeListener(messageListener)
        else messageClient.addListener(messageListener)

        state = state.copy(exerciseEnded = ended)
    }

    init {
        setExerciseEnded(false)
    }
}