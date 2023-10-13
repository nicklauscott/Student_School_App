package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.Gender
import com.xtgem.webuild.fstcawka.models.enums.Grade
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class Student(
    @PrimaryKey(autoGenerate = false)
    val studentId: UUID,
    val regId: String,
    val password: String,
    var firstName: String,
    var lastName: String, var age: Int,
    var guardianEmail: String,
    var guardianMobileNo: String,
    var grade: Grade, var dateOfBirth: LocalDateTime,
    var gender: Gender
)

