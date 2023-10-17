package com.xtgem.webuild.fstcawka.models.constants

import com.xtgem.webuild.fstcawka.models.entities.Student
import java.util.UUID

data class Session(
    val sessionToken: UUID,
    val studentID: UUID
)
