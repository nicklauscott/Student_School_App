package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import java.time.LocalDateTime
import java.util.UUID


@Entity
data class AssignmentResult(
    @PrimaryKey val resultId: UUID,
    val assignmentId: UUID,
    val studentId: UUID,
    val score: Int,
    val subjects: Subjects,
    val questionSize: Int,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val questionWithAnsweredIndex: Map<Int, Int?> = mapOf(0 to null)
)
