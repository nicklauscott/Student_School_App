package com.xtgem.webuild.fstcawka.page.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingData(color: Color = MaterialTheme.colorScheme.primary) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(45.dp), color = color)
    }
}

@Composable
fun LoadingDialog() {
    Card(
        modifier = Modifier
            .size(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Dialog(onDismissRequest = { }) {
            Card {
                Column(modifier = Modifier
                    .padding(5.dp)
                    .size(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingData()
                }
            }
        }
    }
}