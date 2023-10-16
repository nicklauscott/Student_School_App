package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Courses(
    @PrimaryKey val courseId: UUID,
    val title: String,
    val readAmount: Int,
    val likedAmount: Int,
    val dislikeAmount: Int,
    val content: String,
    val imageLink: String
)
