package com.xtgem.webuild.fstcawka.ui.theme.custom

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val darkThemeColors = darkColorScheme(
    background = Color(0xFF303234),
    inverseSurface = Color(0x66000000),  // dark shadow
    inverseOnSurface = Color(0x66494949), // light shadow
    onBackground = Color(0xFFF6F6F7),
    primary = Color(0xFF2395AF),
    secondary = Color(0xFF212329),
    onSecondary = Color(0xFF666769),
    tertiary = Color(0xFFBB86FC),
)


val lightThemeColors = lightColorScheme(
    background = Color(0xFFDCDCDC),
    inverseSurface = Color(0xFFA8B5C7), // dark shadow
    inverseOnSurface = Color(0xFFBEC7D5), // light shadow
    onBackground = Color(0xFF0C0C0C),
    primary = Color(0xFF2395AF),
    secondary = Color(0xFFEEEFF1),
    onSecondary = Color(0xFF717171), //
    tertiary = Color(0xFFBB86FC)  //
)