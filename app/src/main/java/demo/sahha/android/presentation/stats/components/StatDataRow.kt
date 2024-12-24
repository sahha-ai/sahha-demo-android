package demo.sahha.android.presentation.stats.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sdk.sahha.android.domain.model.local_logs.SahhaStat

@Composable
fun StatDataRow(stat: SahhaStat) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stat.type)
            Text("${stat.value} ${stat.unit}")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Divider()
        Spacer(modifier = Modifier.size(10.dp))
    }
}