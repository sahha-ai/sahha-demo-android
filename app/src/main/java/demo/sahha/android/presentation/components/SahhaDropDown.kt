package demo.sahha.android.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import demo.sahha.android.presentation.ui.theme.rubikFamily

@Composable
fun SahhaDropDown(
    label: String,
    options: List<String>,
    existingOption: String? = null,
    modifier: Modifier = Modifier,
    callback: ((selected: String) -> Unit)
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Please select") }

    existingOption?.also {
        selected = it
        callback(selected)
    }

    Column(modifier = modifier) {
        Text(label, fontFamily = rubikFamily)
        Spacer(Modifier.size(5.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(selected, fontFamily = rubikFamily)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Down arrow"
                    )
                }
                DropdownMenu(
                    expanded = expanded, onDismissRequest = { expanded = false },
                    modifier = Modifier.wrapContentSize(),
                ) {
                    for (option in options) {
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                selected = option
                                callback(selected)
                                expanded = false
                            }
                        ) {
                            Text(option, fontFamily = rubikFamily)
                        }
                    }
                }
            }

        }
        Spacer(Modifier.size(20.dp))
    }
}