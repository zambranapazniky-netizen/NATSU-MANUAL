package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val NatsuDarkColorScheme = darkColorScheme(
    primary = NatsuRacingRed,
    onPrimary = NatsuPureWhite,
    primaryContainer = Color(0xFF420A0A),
    onPrimaryContainer = Color(0xFFFFDADA),
    secondary = NatsuIndustrialGray,
    onSecondary = NatsuPureWhite,
    secondaryContainer = JdmSurfaceElevated,
    onSecondaryContainer = JdmTextPrimaryDark,
    tertiary = BlueprintCyanDark,
    onTertiary = Color(0xFF001F29),
    background = JdmCanvasDark,
    onBackground = JdmTextPrimaryDark,
    surface = JdmSurfaceDark,
    onSurface = JdmTextPrimaryDark,
    surfaceVariant = JdmSurfaceElevated,
    onSurfaceVariant = JdmTextSecondaryDark,
    surfaceContainerLowest = Color(0xFF0B0B0D),
    surfaceContainerLow = JdmSurfaceDark,
    surfaceContainer = JdmSurfaceElevated,
    surfaceContainerHigh = JdmSurfaceHighest,
    outline = JdmOutlineDark,
    outlineVariant = Color(0xFF2B2B32)
)

private val NatsuLightColorScheme = lightColorScheme(
    primary = NatsuRacingRed,
    onPrimary = NatsuPureWhite,
    primaryContainer = Color(0xFFFFE5E5),
    onPrimaryContainer = Color(0xFF5C0000),
    secondary = NatsuAnthracite,
    onSecondary = NatsuPureWhite,
    secondaryContainer = SwissSurfaceVariantLight,
    onSecondaryContainer = SwissTextPrimaryLight,
    tertiary = BlueprintCyanLight,
    onTertiary = NatsuPureWhite,
    background = SwissCanvasLight,
    onBackground = SwissTextPrimaryLight,
    surface = SwissSurfaceLight,
    onSurface = SwissTextPrimaryLight,
    surfaceVariant = SwissSurfaceVariantLight,
    onSurfaceVariant = SwissTextSecondaryLight,
    surfaceContainerLowest = NatsuPureWhite,
    surfaceContainerLow = Color(0xFFFAFAFC),
    surfaceContainer = SwissSurfaceVariantLight,
    surfaceContainerHigh = Color(0xFFE0E0E8),
    outline = SwissOutlineLight,
    outlineVariant = Color(0xFFE4E4EC)
)

// Industrial precision corner geometry: sharp enough to feel JDM/Swiss, refined for M3 ergonomics
val NatsuShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(20.dp)
)

@Composable
fun NatsuManualTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) NatsuDarkColorScheme else NatsuLightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                val controller = WindowCompat.getInsetsController(window, view)
                controller.isAppearanceLightStatusBars = !darkTheme
                controller.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = NatsuTypography,
        shapes = NatsuShapes,
        content = content
    )
}
