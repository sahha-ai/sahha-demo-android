package demo.sahha.android.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SahhaLazyRowAndColumn(
    rowVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    columnHorizontalAlignment: Alignment.Horizontal = Alignment.Start,
    columnPadding: Dp = 20.dp,
    modifier: Modifier = Modifier,
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
                item {
                    content()
                }
            }
        }
    }
}

@Composable
fun <T> SahhaLazyRowAndColumnList(
    rowVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    columnHorizontalAlignment: Alignment.Horizontal = Alignment.Start,
    columnPadding: Dp = 20.dp,
    modifier: Modifier = Modifier,
    list: List<T>,
    itemContent: @Composable LazyItemScope.(T) -> Unit
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
                items(list) {
                    itemContent(it)
                }
            }
        }
    }
}