package com.xtgem.webuild.fstcawka.models.enums

enum class Screens(val route: String) {
    Splash("splash"),
    Login("login"),
    Home("Home"),
    NewsLetter("NewsLetter"),
    NewsLetterDetail("NewsLetterDetail"),
    Course("Course"),
    CourseDetail("CourseDetail"),
    Profile("Profile");

    fun withArg(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}