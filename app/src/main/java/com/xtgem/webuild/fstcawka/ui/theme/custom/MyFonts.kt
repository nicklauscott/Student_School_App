package com.xtgem.webuild.fstcawka.ui.theme.custom

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.xtgem.webuild.fstcawka.R

object MyFonts {
    val customFontFamily = listOf(
        FontFamily(Font(R.font.nationale_bold, FontWeight.Bold)),
        FontFamily(Font(R.font.nationale_black, FontWeight.Black)),
        FontFamily(Font(R.font.nationale_demi_bold, FontWeight.SemiBold)),
        FontFamily(Font(R.font.nationale_italic, FontWeight.Thin)),
        FontFamily(Font(R.font.nationale_light, FontWeight.Light)),
        FontFamily(Font(R.font.nationale_medium, FontWeight.Medium)),
        FontFamily(Font(R.font.nationale_regular, FontWeight.Normal))
        )
}