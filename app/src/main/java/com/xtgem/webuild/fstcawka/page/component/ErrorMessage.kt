package com.xtgem.webuild.fstcawka.page.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import kotlinx.coroutines.delay

@Composable
fun ErrorMessage(delay: Long,
                 textColor: Color = MaterialTheme.colorScheme.background,
                 load: Boolean = false, message: String) {
    val showError = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(delay)
        showError.value = true
    }
    if (!showError.value && load) Loader()
    if (showError.value) {
        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = message,
                color = textColor,
                style = TextStyle(fontWeight = FontWeight.Light,
                    fontSize = 20.sp, fontFamily = MyFonts.customFontFamily[2]),
                modifier = Modifier.padding(top = 3.dp))
        }
    }
}

@Composable
fun Loader(color: Color = MaterialTheme.colorScheme.primary) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(45.dp), color = color)
    }
}