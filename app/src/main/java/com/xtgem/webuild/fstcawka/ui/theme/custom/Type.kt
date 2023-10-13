package com.xtgem.webuild.fstcawka.ui.theme.custom

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.xtgem.webuild.fstcawka.ui.theme.Typography

/*
color = Color(0xFF0C0C0C),
                            style = TextStyle(fontWeight = FontWeight.Bold,
                                fontSize = 16.sp, fontFamily = customFontFamily[5])
 */

val lightTypography = Typography(
    headlineLarge = TextStyle(
        color = Color(0xFF0C0C0C),
        fontFamily = MyFonts.customFontFamily[0],
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.2.sp
    ),
    // Other default text styles to override
    headlineMedium = TextStyle(
        color = Color(0xFF0C0C0C),
        fontFamily = MyFonts.customFontFamily[5],
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        color = Color(0xFF0C0C0C),
        fontFamily = MyFonts.customFontFamily[5],
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        color = Color(0xFF444343),
        fontFamily = MyFonts.customFontFamily[2],
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        color = Color(0xFF444343),
        fontFamily = MyFonts.customFontFamily[2],
        fontWeight = FontWeight.Light,
        fontSize = 30.sp,
        lineHeight = 5.sp,
        letterSpacing = (-4).sp
    )
)


val darkTypography = Typography(
    headlineLarge = TextStyle(
        color = Color(0xFFF6F6F7),
        fontFamily = MyFonts.customFontFamily[0],
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.2.sp
    ),
    // Other default text styles to override
    headlineMedium = TextStyle(
        color = Color(0xFFF6F6F7),
        fontFamily = MyFonts.customFontFamily[5],
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        color = Color(0xFFF6F6F7),
        fontFamily = MyFonts.customFontFamily[5],
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        color = Color(0xFF30333B),
        fontFamily = MyFonts.customFontFamily[2],
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        color = Color(0xFF30333B),
        fontFamily = MyFonts.customFontFamily[2],
        fontWeight = FontWeight.Light,
        fontSize = 30.sp,
        lineHeight = 5.sp,
        letterSpacing = (-4).sp
    )


)
