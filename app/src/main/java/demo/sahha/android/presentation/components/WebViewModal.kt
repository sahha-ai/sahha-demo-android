package demo.sahha.android.presentation.components

import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import demo.sahha.android.R

@Composable
fun WebViewModal(
    httpHeader: Map<String, String>? = null,
    url: String,
    title: String? = "Demo WebView",
    icon: @Composable (() -> Unit)? = {
        Image(
            painterResource(id = R.drawable.ic_sahha),
            contentDescription = "Sahha Logo",
            modifier = Modifier.size(25.dp)
        )
    },
    onDismiss: () -> Unit
) {
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
            WebViewContent(
                httpHeader = httpHeader,
                title = title,
                icon = icon,
                url = url, onDismiss = onDismiss
            )
        }
    }
}