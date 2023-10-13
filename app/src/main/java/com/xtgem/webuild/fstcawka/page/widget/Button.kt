package com.xtgem.webuild.fstcawka.page.widget

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gandiva.neumorphic.neu
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultFlatNeuAttrs
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs


@Preview
@Composable
fun PressedButton() {
    Button(
        onClick = { }, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .neu(defaultPressedNetAttrs()),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = null

    ) {
        Text(text = "Button", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun FlatButton() {
    Button(
        onClick = { /*TODO*/ }, modifier = Modifier
            .defaultMinSize(minHeight = 80.dp)
            .fillMaxWidth()
            .padding(16.dp)
            .neu(defaultFlatNeuAttrs()),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = "Button", style = MaterialTheme.typography.bodyMedium
        )
    }
}



@Composable
fun CustomButton1(
    buttonText: String,
    backgroundColor: Color= Color(0xFF0098A6),
    textColor: Color = Color(0xFFE4EAEB),
    modifier: Modifier = Modifier,
    onClick: () -> Unit) {

    Column(modifier = modifier) {
        Button(onClick = { onClick() },
            modifier = modifier
                .padding(3.dp)
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor
            )) {
            Text(text = buttonText,
                modifier = Modifier.padding(4.dp),
                color = textColor,
                style = TextStyle(fontWeight = FontWeight.Bold,
                    fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[2])
            )
        }
    }
}


@Composable
fun CustomButtonWithIcon(
    buttonText: String,
    lefIcon: Boolean,
    icon: ImageVector,
    backgroundColor: Color= Color(0xFF000000),
    textColor: Color = Color(0xFFE4EAEB),
    modifier: Modifier = Modifier,
    onClick: () -> Unit) {

    Column(modifier = modifier) {
        Button(onClick = { onClick() },
            modifier = Modifier
                .padding(3.dp)
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor
            )) {
            if (lefIcon) {
                Icon(icon, contentDescription = buttonText,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 0.dp)
                        .weight(2f)
                )
            }

            Text(text = buttonText,
                modifier = Modifier.padding(
                    bottom = 2.dp, top = 2.dp,
                    start = if (lefIcon) 15.dp else 40.dp,
                    end = if (lefIcon) 0.dp else 8.dp
                ).weight(8f),
                color = textColor,
                style = TextStyle(fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, fontFamily = MyFonts.customFontFamily[0]))

            if (!lefIcon) {
                Icon(icon, contentDescription = buttonText,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(start = 0.dp)
                        .weight(2f)
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomButton3(
    buttonText: String,
    backgroundColor: Color= Color(0xFF2395AF),
    textColor: Color = Color(0xFFE4EAEB),
    modifier: Modifier = Modifier,
    onLongClick: () -> Unit = {},
    onClick: () -> Unit = {}) {
    Column(modifier = modifier) {
        Button(onClick = { },
            modifier = modifier
                .padding(3.dp)
                .fillMaxHeight()
                .fillMaxWidth()
                .combinedClickable (
                    onClick = onClick,
                    onLongClick = onLongClick
                ),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor
            )) {
            Text(text = buttonText,
                modifier = Modifier.padding(4.dp),
                color = textColor,
                style = TextStyle(fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, fontFamily = MyFonts.customFontFamily[0])
            )
        }
    }
}