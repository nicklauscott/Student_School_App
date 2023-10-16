package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.models.enums.CourseReaction
import java.util.UUID

@Entity
data class CourseReactions(
    @PrimaryKey val reactionId: UUID,
    val courseId: UUID,
    val studentId: UUID,
    val reaction: CourseReaction
)
