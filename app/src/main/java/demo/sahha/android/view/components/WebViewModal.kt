package demo.sahha.android.view.components

import android.view.Gravity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import demo.sahha.android.view.components.WebViewContent

@Composable
fun WebViewModal(url: String, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        val dialogProvider = LocalView.current.parent as DialogWindowProvider
        dialogProvider.window.setGravity(Gravity.BOTTOM)

        Card(
            modifier = Modifier
                .fillMaxHeight(0.9f),
            shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8)
        ) {
            WebViewContent(url = url, onDismiss = onDismiss)
        }
    }
}