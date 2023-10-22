package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID

@Entity
data class UserSession(
    @PrimaryKey val id: UUID,
    val studentID: UUID,
    val sessionToken: UUID,
    val active: Boolean,
    val creationDate: LocalDateTime
) {
    fun sessionValidity() = (calculateMinutes(creationDate, LocalDateTime.now()) <= 60 && active)

    // fun sessionValidity() = (ChronoUnit.DAYS.between(creationDate, LocalDateTime.now()) <= 1 && active)
    private fun calculateMinutes(dateTime1: LocalDateTime, dateTime2: LocalDateTime): Long {
        val duration = Duration.between(dateTime1, dateTime2)
        return duration.toMinutes()
    }
}


data class LoginPreference(
    val regLogin: Boolean = true,
    val regId: String = "82736",
    val studentId: String = "ae4c602d-a7aa-47af-a018-30ec6a5c67ca",
    val password: String = "67fyRI3BJSVkslS",
    val sessionToken: String = "635a2a58-0cd2-4e8f-a8ba-870e4e13ff46"
)