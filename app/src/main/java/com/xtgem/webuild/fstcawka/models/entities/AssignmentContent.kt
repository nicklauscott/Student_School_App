package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class AssignmentContent(
    @PrimaryKey val contentId: UUID = UUID.randomUUID(),
    val assignmentId: UUID,
    val questionIndex: Int,
    val question: String,
    val options: List<String>,
    val answersIndex: String,
    val creationDate: LocalDateTime? = LocalDateTime.now(),
    val point: Int? = (1..3).random())
