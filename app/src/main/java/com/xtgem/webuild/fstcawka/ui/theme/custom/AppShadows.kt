package com.xtgem.webuild.fstcawka.ui.theme.custom

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.NeuAttrs
import com.gandiva.neumorphic.shape.CornerShape
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner

object AppShadows {
    val Purple200 = Color(0xFFBB86FC)
    val Purple500 = Color(0xFF6200EE)

    object Light {
        val Background = Color(0xFFDCDCDC)
        val LightShadow = Color(0xFFFFFFFF)
        val DarkShadow = Color(0xFFA8B5C7)
        val TextColor = Color.Black
    }

    object Dark {
        val Background = Color(0xFF303234)
        val LightShadow = Color(0x66494949)
        val DarkShadow = Color(0x66000000)
        val TextColor = Color.White
    }

    @Composable
    fun lightShadow() = if (!isSystemInDarkTheme()) Light.LightShadow else Dark.LightShadow

    @Composable
    fun darkShadow() = if (!isSystemInDarkTheme()) Light.DarkShadow else Dark.DarkShadow
}

@Composable
fun defaultPressedNetAttrs(
    elevation: Dp = 6.dp,
    cornerShape: CornerShape = RoundedCorner(12.dp)
) = NeuAttrs(
    lightShadowColor = AppShadows.lightShadow(),
    darkShadowColor = AppShadows.darkShadow(),
    shadowElevation = elevation,
    lightSource = LightSource.LEFT_TOP,
    shape = Pressed(cornerShape),
)

@Composable
fun defaultFlatNeuAttrs(
    elevation: Dp = 6.dp,
    cornerShape: CornerShape = RoundedCorner(12.dp)
) = NeuAttrs(
    lightShadowColor = AppShadows.lightShadow(),
    darkShadowColor = AppShadows.darkShadow(),
    shadowElevation = elevation,
    lightSource = LightSource.LEFT_TOP,
    shape = Flat(cornerShape)
)