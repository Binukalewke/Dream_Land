package com.example.movienew.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// icon color change during dark and light modes
val DarkIcon = Color.White
val LightIcon = Color.Black

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E6CE3),
    secondary = PurpleGrey80,
    tertiary = Pink80,
    onBackground = DarkIcon
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E6CE3),
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onBackground = LightIcon
)





@Composable
fun MovieNewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
