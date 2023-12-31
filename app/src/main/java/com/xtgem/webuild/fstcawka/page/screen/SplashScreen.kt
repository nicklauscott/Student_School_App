package com.xtgem.webuild.fstcawka.page.screen


import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xtgem.webuild.fstcawka.R
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.page.viewmodel.SplashScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = viewModel<SplashScreenViewModel>()
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)

        if (viewModel.isSessionValid()) {
            Log.d("profile-rings", "1 isSessionValid: ${viewModel.isSessionValid()}")
            navController.navigate(Screens.Home.withArg(
                viewModel.getSessionDetail().studentID.toString(),
                viewModel.getSessionDetail().sessionToken.toString()
            ))
        }else {
            Log.d("profile-rings", "2 isSessionValid: ${viewModel.isSessionValid()}")
            navController.navigate(Screens.Login.route)
        }

    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null,
            modifier = Modifier.scale(scale.value)
            )
    }
}