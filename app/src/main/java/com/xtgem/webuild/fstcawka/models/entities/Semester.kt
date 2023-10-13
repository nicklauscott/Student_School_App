package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import java.util.UUID

@Entity
data class Semester(
    @PrimaryKey(autoGenerate = false)
    val semesterId: UUID,
    val subjectId: UUID,
    val subject: Subjects,
    val studentId: UUID,
    val semester: Semesters,
    val midTermScore: Int?,
    val welcomeBackTest: Int?,
    val assignmentsScore: Int?,
    val examScore: Int?,
    val totalScore: Int?,
    val overallScore: Int = 100
)
