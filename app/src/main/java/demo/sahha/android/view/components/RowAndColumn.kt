package demo.sahha.android.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RowAndColumn(
    rowVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    columnHorizontalAlignment: Alignment.Horizontal = Alignment.Start,
    columnPadding: Dp = 20.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Row(
        verticalAlignment = rowVerticalAlignment,
    ) {
        Column(
            horizontalAlignment = columnHorizontalAlignment,
            modifier = Modifier
                .fillMaxWidth()
                .padding(columnPadding),
            content = content
        )
    }
}