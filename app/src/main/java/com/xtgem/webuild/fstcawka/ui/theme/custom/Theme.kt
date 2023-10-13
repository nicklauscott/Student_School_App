package com.xtgem.webuild.fstcawka.ui.theme.custom

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun Theme(content: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = if (isDarkTheme) darkThemeColors else lightThemeColors,
        typography = if (isDarkTheme) darkTypography else lightTypography,
        content = content
    )
}