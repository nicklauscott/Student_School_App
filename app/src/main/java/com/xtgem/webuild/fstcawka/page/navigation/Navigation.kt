package com.xtgem.webuild.fstcawka.page.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.page.screen.AssignmentDetail
import com.xtgem.webuild.fstcawka.page.screen.AssignmentListScreen
import com.xtgem.webuild.fstcawka.page.screen.CourseDetailScreen
import com.xtgem.webuild.fstcawka.page.screen.CourseListScreen
import com.xtgem.webuild.fstcawka.page.screen.HomeScreen
import com.xtgem.webuild.fstcawka.page.screen.LoginScreen
import com.xtgem.webuild.fstcawka.page.screen.NewsLetterDetailScreen
import com.xtgem.webuild.fstcawka.page.screen.NewsLetterScreen
import com.xtgem.webuild.fstcawka.page.screen.ProfileScreen
import com.xtgem.webuild.fstcawka.page.screen.ResultDetailScreen
import com.xtgem.webuild.fstcawka.page.screen.ResultScreen
import com.xtgem.webuild.fstcawka.page.screen.SchoolBillScreen
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
            route = Screens.Home.route + "/{userId}/{tokenId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("tokenId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val sessionToken = entry.arguments?.getString("tokenId")
            entry.arguments?.getString("userId")?.let {
                if (sessionToken != null) {
                    HomeScreen(userId = it, sessionToken = sessionToken,
                        navController = navController)
                }
            }
        }

        // News Letter
        composable(
            route = Screens.NewsLetter.route + "/{userId}/{tokenId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("tokenId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val sessionToken = entry.arguments?.getString("tokenId")
            entry.arguments?.getString("userId")?.let {
                if (sessionToken != null) {
                    NewsLetterScreen(userId = it, sessionToken = sessionToken,
                        navController = navController)
                }
            }
        }

        // News Detail
        composable(
            route = Screens.NewsLetterDetail.route + "/{newsId}",
            arguments = listOf(
                navArgument("newsId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            entry.arguments?.getString("newsId")?.let { NewsLetterDetailScreen(newsId = it) }
        }


        // Course
        composable(
            route = Screens.Course.route + "/{studentId}",
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            entry.arguments?.getString("studentId")?.let { CourseListScreen(studentId = it, navController) }
        }

        // Course Detail
        composable(
            route = Screens.CourseDetail.route + "/{courseId}/{userId}",
            arguments = listOf(
                navArgument("courseId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val userId = entry.arguments?.getString("userId")
            entry.arguments?.getString("courseId")?.let {
                if (userId != null) {
                    CourseDetailScreen(courseId = it,
                        studentId = userId)
                }
            }
        }


        // Profile
        composable(
            route = Screens.Profile.route + "/{userId}/{tokenId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("tokenId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val sessionToken = entry.arguments?.getString("tokenId")
            entry.arguments?.getString("userId")?.let {
                if (sessionToken != null) {
                    ProfileScreen(userId = it, sessionToken = sessionToken,
                        navController = navController)
                }
            }
        }


        // Bill
        composable(
            route = Screens.Bill.route + "/{userId}/{tokenId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("tokenId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val sessionToken = entry.arguments?.getString("tokenId")
            entry.arguments?.getString("userId")?.let {
                if (sessionToken != null) {
                    SchoolBillScreen(userId = it, sessionToken = sessionToken)
                }
            }
        }


        // Assignment
        composable(
            route = Screens.Assignment.route + "/{userId}/{tokenId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("tokenId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val sessionToken = entry.arguments?.getString("tokenId")
            entry.arguments?.getString("userId")?.let {
                if (sessionToken != null) {
                    AssignmentListScreen(userId = it, sessionToken = sessionToken,
                        navController = navController)
                }
            }
        }

        // Assignment Detail
        composable(
            route = Screens.AssignmentDetail.route + "/{userId}/{tokenId}/{assignmentId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("tokenId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("assignmentId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val sessionToken = entry.arguments?.getString("tokenId")
            val assignmentId = entry.arguments?.getString("assignmentId")
            entry.arguments?.getString("userId")?.let {
                if (sessionToken != null) {
                    if (assignmentId != null) {
                        AssignmentDetail(userId = it, sessionToken = sessionToken,
                            assignmentId = assignmentId)
                    }
                }
            }
        }


        // Result
        composable(
            route = Screens.Result.route + "/{userId}/{tokenId}/{subject}/{semester}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("tokenId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("subject") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("semester") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val sessionToken = entry.arguments?.getString("tokenId")
            val subject = entry.arguments?.getString("subject")
            val semester = entry.arguments?.getString("semester")
            entry.arguments?.getString("userId")?.let {
                if (sessionToken != null) {
                    if (subject != null) {
                        if (semester != null) {
                            ResultScreen(userId = it, sessionToken = sessionToken,
                                subject = subject, semester = semester, navController = navController)
                        }
                    }
                }
            }
        }

        // Result Detail
        composable(
            route = Screens.ResultDetail.route + "/{userId}/{tokenId}/{resultId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("tokenId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                },
                navArgument("resultId") {
                    type = NavType.StringType
                    defaultValue = UUID.randomUUID().toString()
                    nullable = true
                }
            )
        ) { entry ->
            val sessionToken = entry.arguments?.getString("tokenId")
            val resultId = entry.arguments?.getString("resultId")
            entry.arguments?.getString("userId")?.let {
                if (sessionToken != null) {
                    if (resultId != null) {
                        ResultDetailScreen(userId = it, sessionToken = sessionToken,
                            resultId = resultId)
                    }
                }
            }
        }
    }
}