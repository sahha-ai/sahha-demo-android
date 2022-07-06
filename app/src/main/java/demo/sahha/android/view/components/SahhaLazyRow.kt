package demo.sahha.android.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SahhaLazyRow(
    rowVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    columnHorizontalAlignment: Alignment.Horizontal = Alignment.Start,
    columnPadding: Dp = 20.dp,
    content: @Composable LazyItemScope.() -> Unit
) {
    LazyRow(
        verticalAlignment = rowVerticalAlignment,
    ) {
        item {
            LazyColumn(
                horizontalAlignment = columnHorizontalAlignment,
                modifier = Modifier
                    .fillParentMaxSize()
                    .padding(columnPadding),
            ) {
                item(content = content)
            }
        }
    }
}