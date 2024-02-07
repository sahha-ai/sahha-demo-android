import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingCircle(
    alignment: Alignment = Alignment.Center,
    strokeWidth: Dp = 4.dp,
    loadingCircleColor: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = MaterialTheme.colors.background,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = alignment,
    ) {
        CircularProgressIndicator(
            strokeWidth = strokeWidth,
            color = loadingCircleColor
        )
    }
}