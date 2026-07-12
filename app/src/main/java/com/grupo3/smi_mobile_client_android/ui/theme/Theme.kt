package com.grupo3.smi_mobile_client_android.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = SmiRed,
    onPrimary = Color.White,
    secondary = SmiRed,
    background = SmiDarkBackground,
    surface = SmiDarkSurface,
    error = SmiRed,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = SmiRed,
    onPrimary = Color.White,
    secondary = SmiRed,
    background = SmiBackground,
    surface = SmiSurfaceWhite,
    onBackground = SmiTextPrimary,
    onSurface = SmiTextPrimary,
    error = SmiRed,
    onError = Color.White
)

@Composable
fun SmimobileclientandroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
