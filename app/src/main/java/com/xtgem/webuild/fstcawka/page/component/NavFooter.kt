package com.xtgem.webuild.fstcawka.page.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xtgem.webuild.fstcawka.R
import com.xtgem.webuild.fstcawka.models.enums.Screens

@Composable
fun NavFooter(screen: Screens,
           onHomeClick: () -> Unit = {},
           onNewsClick: () -> Unit = {},
           onProfileClick: () -> Unit = {}) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(65.dp)
        .background(color = MaterialTheme.colorScheme.inverseOnSurface)
        .padding(top = 10.dp)) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .weight(1f).padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier
                .clickable {
                    onHomeClick()
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(modifier = Modifier.weight(0.5f)) {
                    val homeIcon = if (screen == Screens.Home) {
                        painterResource(id = R.drawable.ic_home_24)
                    }else if (isSystemInDarkTheme()) {
                        painterResource(id = R.drawable.ic_home_24_dark)
                    }else {
                        painterResource(id = R.drawable.ic_home_24_light)
                    }
                    Image(
                        painter = homeIcon,
                        contentDescription = "Home",
                        modifier = Modifier.size(25.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Column(modifier = Modifier.weight(0.5f)) {
                    Text(text = "Home Icon",
                        color = if (screen == Screens.Home) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineSmall)
                }
            }
        }

        Column(modifier = Modifier
            .fillMaxHeight()
            .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier
                .clickable {
                    onNewsClick()
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(modifier = Modifier.weight(0.5f)) {
                    val newsLetterIcon = if (screen == Screens.NewsLetter) {
                        painterResource(id = R.drawable.ic_newsletter)
                    }else if (isSystemInDarkTheme()) {
                        painterResource(id = R.drawable.ic_newsletter_dark)
                    }else {
                        painterResource(id = R.drawable.ic_newsletter_light)
                    }
                    Image(
                        painter = newsLetterIcon,
                        contentDescription = "News Letter Icon",
                        modifier = Modifier.size(25.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Column(modifier = Modifier.weight(0.5f)) {
                    Text(text = "News Letter",
                        color = if (screen == Screens.NewsLetter) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineSmall)
                }
            }
        }

        Column(modifier = Modifier
            .fillMaxHeight()
            .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier
                .clickable {
                    onProfileClick()
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(modifier = Modifier.weight(0.5f)) {
                    val profileIcon = if (screen == Screens.Profile) {
                        painterResource(id = R.drawable.ic_account_circle_24)
                    }else if (isSystemInDarkTheme()) {
                        painterResource(id = R.drawable.ic_account_circle_24_dark)
                    }else {
                        painterResource(id = R.drawable.ic_account_circle_24_light)
                    }
                    Image(
                        painter = profileIcon,
                        contentDescription = "Profile Icon",
                        modifier = Modifier.size(25.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Column(modifier = Modifier.weight(0.5f)) {
                    Text(text = "Profile",
                        color = if (screen == Screens.Profile) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}