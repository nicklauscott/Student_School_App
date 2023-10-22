package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import java.util.UUID
@Entity
data class StudentResult(
    @PrimaryKey(autoGenerate = false)
    val resultId: UUID,
    val studentId: UUID,
    val subject: Subjects,
    val semester: Semesters,
    val midTermScore: Int?,
    val welcomeBackTest: Int?,
    val assignmentsScore: Int?,
    val examScore: Int?,
    val totalScore: Int?,
    val overallScore: Int = 100
) {
    fun averageScore() = ((midTermScore ?: 0) + (welcomeBackTest ?: 0) + (assignmentsScore ?: 0) + (examScore ?: 0)) / 4
}

