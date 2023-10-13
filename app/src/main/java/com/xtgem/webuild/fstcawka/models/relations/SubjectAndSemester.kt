package com.xtgem.webuild.fstcawka.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.xtgem.webuild.fstcawka.models.entities.Semester
import com.xtgem.webuild.fstcawka.models.entities.Subject

data class AllSubjectAndSAllSemesters(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "studentId"
    )
    val semester: List<Semester>
)

data class ASubjectAndItsSemesters(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "subjectId"
    )
    val semester: List<Semester>
)

