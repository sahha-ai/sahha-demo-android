package demo.sahha.android.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import demo.sahha.android.presentation.ui.theme.rubikFamily

@Composable
fun SahhaThemeButton(
    bottomSpace: Dp = 10.dp,
    buttonTitle: String, onClick: (() -> Unit)
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25),
    ) {
        Text(buttonTitle, fontFamily = rubikFamily, modifier = Modifier.padding(vertical = 10.dp))
    }
    Spacer(Modifier.size(bottomSpace))
}