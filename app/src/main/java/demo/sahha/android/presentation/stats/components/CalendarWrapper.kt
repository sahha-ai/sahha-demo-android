package demo.sahha.android.presentation.stats.components

import android.widget.CalendarView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CalendarWrapper(
    onDateChange: (year: Int, month: Int, dayOfMonth: Int) -> Unit,
) {
    AndroidView(
        factory = { context ->
            CalendarView(context).apply {
                setOnDateChangeListener { _, year, month, dayOfMonth ->
                    onDateChange(year, month + 1, dayOfMonth)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}