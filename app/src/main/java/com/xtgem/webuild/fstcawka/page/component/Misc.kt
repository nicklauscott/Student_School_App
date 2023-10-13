package com.xtgem.webuild.fstcawka.page.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.NeuAttrs
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.CornerShape
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.xtgem.webuild.fstcawka.ui.theme.custom.AppShadows


val defaultWidgetPadding = 16.dp
val elevation = 6.dp
val cornerShape: CornerShape = RoundedCorner(12.dp)

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
fun defaultFlatNeuAttrs1(
    elevation: Dp = 6.dp,
    cornerShape: CornerShape = RoundedCorner(12.dp)
) = NeuAttrs(
    lightShadowColor = Color(0x66494949),
    darkShadowColor = Color(0x66000000),
    shadowElevation = elevation,
    lightSource = LightSource.LEFT_TOP,
    shape = Flat(cornerShape)
)

@Composable
fun DipOrHoleSimulation() {
    Column(modifier = Modifier
        .size(300.dp)
        .background(color = Color(0xFF303234)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Card(
            modifier = Modifier
                .size(128.dp)
                .neu(defaultFlatNeuAttrs1()),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF303234)
            )
        ){
            // Add child components here.

        }
    }
}




@Preview
@Composable
fun DipOrHoleSimulationPreview() {
    DipOrHoleSimulation()
}
