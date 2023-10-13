package com.xtgem.webuild.fstcawka.page.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.page.screen.HomeScreen
import com.xtgem.webuild.fstcawka.page.screen.LoginScreen
import com.xtgem.webuild.fstcawka.page.screen.NewsLetterScreen
import com.xtgem.webuild.fstcawka.page.screen.ProfileScreen
import com.xtgem.webuild.fstcawka.page.screen.SplashScreen
import java.util.UUID

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }

        // Login
        composable(route = Screens.Login.route) {
            LoginScreen(navController)
        }

        // Home
        composable(
            route = Screens.Home.route + "/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            entry.arguments?.getString("userId")?.let { HomeScreen(id = it,
                navController = navController) }
        }

        // News
        composable(
            route = Screens.NewsLetter.route + "/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            entry.arguments?.getString("userId")?.let { NewsLetterScreen(userId = it,
                navController = navController) }
        }

        // Profile
        composable(
            route = Screens.Profile.route + "/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            entry.arguments?.getString("userId")?.let { ProfileScreen(userId = it,
                navController = navController) }
        }

    }
}