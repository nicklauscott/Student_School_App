package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import java.util.UUID

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = false)
    val subjectId: UUID,
    val subjects: Subjects,
    val studentId: UUID
)
