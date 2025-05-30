package demo.sahha.android.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = SahhaDarkest,
    onPrimary = Color.White,
    primaryVariant = SahhaGray,
    secondary = SahhaLightGray,
    background = SahhaDark,
    onBackground = Color.White
)

private val LightColorPalette = lightColors(
    primary = SahhaDarkest,
    onPrimary = Color.White,
    primaryVariant = SahhaDark,
    secondary = SahhaLightGray,
    background = Color.White,
    onBackground = Color.Black

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SahhaexampleandroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
//        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}