package demo.sahha.android.presentation.permission

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaSensor
import sdk.sahha.android.source.SahhaSensorStatus
import javax.inject.Inject

class PermissionViewModel @Inject constructor(

) : ViewModel() {
    val activityRecognitionStatus = mutableStateOf(SahhaSensorStatus.pending.name)
    private val sensors = SahhaSensor.values().toSet()

    fun getSensorStatus(context: Context) {
        Sahha.getSensorStatus(context, sensors) { _, newStatus ->
            try {
                activityRecognitionStatus.value = newStatus.name
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun enableSensors(context: Context) {
        Sahha.enableSensors(context, sensors) { _, newStatus ->
            try {
                activityRecognitionStatus.value = newStatus.name
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun openAppSettings(context: Context) {
        Sahha.openAppSettings(context)
    }
}

