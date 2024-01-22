package demo.sahha.android.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import demo.sahha.android.presentation.ui.theme.rubikFamily

@Composable
fun SahhaTextField(
    value: String,
    label: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = keyboardType
    ),
    keyboardActions: KeyboardActions,
    bottomSpacer: Dp = 10.dp,
    callback: ((value: String) -> Unit),
) {
    OutlinedTextField(
        value = value,
        onValueChange = { callback(it) },
        label = {
            Text(
                label, fontFamily = rubikFamily,
                fontSize = 14.sp
            )
        },
        shape = RoundedCornerShape(25),
        modifier = Modifier.fillMaxWidth(),
        singleLine = isSingleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
    Spacer(Modifier.size(bottomSpacer))
}