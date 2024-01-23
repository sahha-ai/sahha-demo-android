package demo.sahha.android.presentation.permission

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaSensorStatus
import javax.inject.Inject

class PermissionViewModel @Inject constructor(

) : ViewModel() {
    val activityRecognitionStatus = mutableStateOf(SahhaSensorStatus.pending.name)

    fun getSensorStatus(context: Context) {
        Sahha.getSensorStatus(context) { _, newStatus ->
            activityRecognitionStatus.value = newStatus.name
        }
    }

    fun enableSensors(context: Context) {
        Sahha.enableSensors(context) { _, newStatus ->
            activityRecognitionStatus.value = newStatus.name
        }
    }

    fun openAppSettings(context: Context) {
        Sahha.openAppSettings(context)
    }
}

