package com.xtgem.webuild.fstcawka.database

import android.database.Cursor
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.Gender
import com.xtgem.webuild.fstcawka.models.enums.Grade
import com.xtgem.webuild.fstcawka.models.enums.PaymentMethod
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    // Date
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let {
            return formatter.parse(value, LocalDateTime::from)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toTimestamp(date: LocalDateTime?): String? {
        return date?.format(formatter)
    }

    // Grade
    @TypeConverter
    fun fromGrade(value: Grade): String{
        return value.name
    }

    @TypeConverter
    fun toGrade(value: String): Grade{
        return  Grade.valueOf(value)
    }

    // Gender
    @TypeConverter
    fun fromGender(value: Gender): String{
        return value.name
    }

    @TypeConverter
    fun toGender(value: String): Gender{
        return  Gender.valueOf(value)
    }

    // Subject
    @TypeConverter
    fun fromSubject(value: Subjects): String{
        return value.name
    }

    @TypeConverter
    fun toSubject(value: String): Subjects{
        return  Subjects.valueOf(value)
    }

    // Semester
    @TypeConverter
    fun fromSemester(value: Semesters): String{
        return value.name
    }

    @TypeConverter
    fun toSemester(value: String): Semesters{
        return  Semesters.valueOf(value)
    }

    // Bills
    @TypeConverter
    fun fromBills(value: Bills): String{
        return value.name
    }

    @TypeConverter
    fun toBills(value: String): Bills{
        return  Bills.valueOf(value)
    }

    // Payment Methods
    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod): String{
        return value.name
    }

    @TypeConverter
    fun toPaymentMethod(value: String): PaymentMethod{
        return  PaymentMethod.valueOf(value)
    }

    // List to Map
    @TypeConverter
    fun fromListMap(value: Map<Int, Int?>?): String {
        return if (!value.isNullOrEmpty()) value.toString()
        else "{1=0, 2=null, 3=2, 4=null, 5=null}"
    }

    @TypeConverter
    fun toListMap(value: String?): Map<Int, Int?> {
        if (value != null && value.trim().isBlank()) {
            return mapOf(0 to null, 1 to null, 2 to null)
        }
        val splitToMaps = value!!.split(",")
        val newMap = mutableMapOf<Int, Int?>()
        for (i in splitToMaps) {
            val removeCurly = i.replace(Regex("[{}]", RegexOption.MULTILINE), "")
            val keyAndValue = removeCurly.split("=")
            if (keyAndValue.size == 2) {
                val key = try {
                    keyAndValue[0].trim().toInt()
                } catch (e: NumberFormatException) {
                    // Handle invalid key here, like skipping or setting a default value
                    continue // Skip this entry and proceed with the next one
                }
                val content: Int? = if (keyAndValue[1].trim() == "null") null else keyAndValue[1].trim().toInt()
                newMap[key] = content
            }
        }
        return newMap.ifEmpty { mapOf(0 to null, 1 to null, 2 to null) }
    }

    // String List
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(separator = ",") { it.toString() }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String> {
        val paymentIdList = mutableListOf<String>()
        val ids = value?.split(",")
        if (ids != null) {
            for (i in ids) {
                paymentIdList.add(i)
            }
        }
        return paymentIdList
    }

}