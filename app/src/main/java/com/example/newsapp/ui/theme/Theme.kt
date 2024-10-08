package com.example.newsapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PRIMARY_DARK,
    secondary = SECONDARY_DARK,
    surface = SURFACE_DARK,
    background = BACKGROUND_DARK,
    onPrimary = ON_PRIMARY_DARK,
    onSecondary = ON_SECONDARY_DARK,
    onSurface = ON_SURFACE_DARK,
    onSurfaceVariant = ON_SURFACE_VARIANT_DARK,
    onBackground = ON_BACKGROUND_DARK,
    outline = OUTLINE_DARK,
)

private val LightColorScheme = lightColorScheme(
    primary = PRIMARY_LIGHT,
    secondary = SECONDARY_LIGHT,
    surface = SURFACE_LIGHT,
    background = BACKGROUND_LIGHT,
    onPrimary = ON_PRIMARY_LIGHT,
    onSecondary = ON_SECONDARY_LIGHT,
    onSurface = ON_SURFACE_LIGHT,
    onSurfaceVariant = ON_SURFACE_VARIANT_LIGHT,
    onBackground = ON_BACKGROUND_LIGHT,
    outline = OUTLINE_LIGHT,
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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