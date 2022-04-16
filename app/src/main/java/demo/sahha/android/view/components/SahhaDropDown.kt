package demo.sahha.android.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import demo.sahha.android.view.ui.theme.rubikFamily

@Composable
fun SahhaDropDown(label: String, options: List<String>, callback: ((selected: String) -> Unit)) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Please select") }

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
                            callback(option)
                            selected = option
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