package com.xtgem.webuild.fstcawka.misc

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.xtgem.webuild.fstcawka.Repository
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class Uncategorized {

    val nairaSign = "₦"

    private val mapOfNews = mutableMapOf<Int, List<String>>()

    val emailDomains = mutableListOf(
        "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "aol.com", "icloud.com",
        "mail.com", "protonmail.com", "yandex.com", "zoho.com", "gmx.com", "rediffmail.com",
        "mail.ru", "outlook.in", "live.com", "comcast.net", "qq.com", "sina.com", "163.com",
        "lavabit.com"
    )


    private val messages = mutableListOf("Hello", "How are you?", "What's up?", "Nice to meet you", "What's new?", "How's your day going?", "Good morning", "Good afternoon", "Good evening")

    init {

        mapOfNews[1] = listOf("The Benefits and Challenges of Online Learning ", "With the rise of online learning due to the COVID-19 pandemic, there has been a lot of discussion...")
        mapOfNews[2] = listOf("The Importance of Emotional Intelligence in Education" , "While academic intelligence is essential for success in school, emotional intelligence is...")
        mapOfNews[3] = listOf("Exploring Alternative Forms of Assessment " , "Traditional forms of assessment, such as exams and quizzes, are often the go-to methods for evaluating...")
        mapOfNews[4] = listOf("The Impact of Social Media on Student Mental Health " , "Social media has become an integral part of many students' lives, but it can also have a negative...")
        mapOfNews[5] = listOf("The Importance of Culturally Responsive Teaching " , "Culturally responsive teaching is an approach to education that recognizes and values the diverse...")
        mapOfNews[6] = listOf("The Impact of Student-Centered Learning on Student Achievement:" , "Student-centered learning is an approach to education that prioritizes student choice... ")
        mapOfNews[7] = listOf("The Role of Teachers in Supporting Student Mental Health " , "Teachers play a crucial role in promoting student well-being and mental health...")
        mapOfNews[8] = listOf("The Benefits of Multilingualism in Education " , "Learning a second language can have numerous benefits for students, including improved..")
        mapOfNews[9] = listOf("The Impact of Physical Activity on Academic Achievement" , "Regular physical activity has been shown to improve academic performance, cognitive skills...")
        mapOfNews[10] = listOf("The Ethics of Artificial Intelligence in Education" , "Artificial intelligence (AI) is becoming increasingly prevalent in education, with applications...")

    }

    fun selectNews(number: Int): List<String>{
        val newsTitleAndDetail = mutableListOf<String>()
            for ((k, v) in mapOfNews) {
                if (number == k) {
                    newsTitleAndDetail.add(v[0])
                    newsTitleAndDetail.add(v[1])
                }
            }
        return newsTitleAndDetail
    }

    fun generateRandomDate(): LocalDate {
        val startDate = LocalDate.of(2020, 1, 1)
        val endDate = LocalDate.of(2021, 12, 31)
        val randomDay = ThreadLocalRandom.current().nextInt(0, endDate.dayOfYear - startDate.dayOfYear + 1)
        return startDate.plusDays(randomDay.toLong())
    }


    fun getDrawableBackgroundColor(context: Context, drawableId: Int, x: Int, y: Int): Int {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        val bitmap = (drawable as BitmapDrawable).bitmap
        return bitmap.getPixel(x, y)
    }

    fun createGradientDrawable(color: Int): GradientDrawable{
        val cornerRadius = 25f // Set the corner radius of the view
        val shape = GradientDrawable()
        shape.cornerRadius = cornerRadius
        shape.setColor(color) // Set the background color of the view
        return shape
    }

    fun createSolidDrawable(color: Int): GradientDrawable {
        val cornerRadius = 25f // Set the corner radius of the view
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = cornerRadius
        shape.setColor(color) // Set the background color of the view
        return shape
    }




    fun convertLocateDateTimeToString(localDateTime: LocalDateTime): Date {
        val localDate = localDateTime.toLocalDate()
        val epochDay = localDate.toEpochDay()
        return Date(epochDay * 86400000)
    }

    fun datePicker(context: Context): String{
        var date = ""
        // Get the current date
        val currentDate = Calendar.getInstance()
        val years = currentDate.get(Calendar.YEAR)
        val months = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

// Create a date picker dialog
        val datePicker = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val localDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0)
            val formattedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            date = formattedDateTime
            return@OnDateSetListener
        }, years, months, day)

// Show the dialog
        datePicker.show()

        return date
    }

    fun simpleDateFormatter(inputDate: LocalDateTime): String {
        val date = mutableListOf<String>()
        date.add(inputDate.year.toString())
        date.add(inputDate.month.value.toString())
        date.add(inputDate.dayOfMonth.toString())
        return date.joinToString(separator = "-")
    }

    fun validatePassword(input: EditText): Boolean{
        return if (input.text.toString().length >= 6) {
            true
        }else{
           input.error = "WEAK PASSWORD"
            false
        }
    }

    fun validateEmail(input: EditText): Boolean{
        return if (input.text.toString().matches(Regex("[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}"))) {
            true
        }else {
            input.error = "INVALID EMAIL ADDRESS"
            false
        }
    }

    fun validateMobileNumber(input: EditText): Boolean{
        return if (input.text.toString().length == 13) {
            true
        }else {
            input.error = "INVALID MOBILE NO"
            return false
        }
    }

    fun validateNames(input: EditText): Boolean{
        for (i in input.text.toString()) {
            if (i == ' ') {
                input.error = "WHITE SPACE NOT ALLOWED"
                return false
            }
        }
        return true
    }

    fun calculateAge(birthDateTime: LocalDateTime): Int {
        val currentDate = LocalDateTime.now()
        val birthDate = birthDateTime.toLocalDate()
        return Period.between(birthDate, currentDate.toLocalDate()).years
    }

    fun generateRandomLocalDateTime(haveRange: Boolean? = null): LocalDateTime{
        val random = Random()
        val calender = Calendar.getInstance()
        val startYear = 2005
        val endYear = 2012
        var year = random.nextInt(endYear - startYear + 1) + startYear
        if (haveRange != null) {
            year = calender.get(Calendar.YEAR)
        }
        val month = random.nextInt(12) + 1
        val day = random.nextInt(28) + 1
        val hour = random.nextInt(24)
        val minute = random.nextInt(60)
        val second = random.nextInt(60)
        return LocalDateTime.of(year, Month.of(month), day, hour, minute, second)
    }

    fun calculateAgeFormLocalDate(birthDateTime: LocalDate): Int {
        val currentDate = LocalDateTime.now()
        return Period.between(birthDateTime, currentDate.toLocalDate()).years
    }

    fun convertHexToColorInt(hexColor: String): Int {
        return try {
            Color.parseColor(hexColor)
        } catch (e: IllegalArgumentException) {
            // Handle invalid color format
            Color.TRANSPARENT // Return a default color (e.g., transparent) in case of error
        }
    }


    companion object {

        fun hidePassword(password: String): String{
            val list = mutableListOf<String>()
            val calculate = ((500..5000).random() / (60..350).random().toDouble())
                + password.length - (3..8).random()
            for (i in 0 until calculate.toInt()) {
                list.add("*")
            }
            return list.joinToString("")
        }
        fun hashPassword(password: String): String{
            val salt = BCrypt.gensalt()
            return BCrypt.hashpw(password, salt)
        }

        fun verifyPassword(textPassword: String, hashedPassword: String): Boolean{
            return BCrypt.checkpw(textPassword, hashedPassword)
        }

    }

}