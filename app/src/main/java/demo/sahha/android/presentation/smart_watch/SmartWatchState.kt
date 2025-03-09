package demo.sahha.android.presentation.smart_watch

data class SmartWatchState(
    val isLoading: Boolean = false,
    val exerciseEnded: Boolean = false,
    val exerciseName: String = "Unknown exercise",
    val heartRate: String = "-",
    val currentDuration: String = "-",
    val min: String = "-",
    val max: String = "-",
    val average: String = "-",
    val start: String = "-",
    val end: String = "-",
    val totalDuration: String = "-",
)
