package com.xtgem.webuild.fstcawka.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.xtgem.webuild.fstcawka.models.entities.Assignment
import com.xtgem.webuild.fstcawka.models.entities.AssignmentContent
import com.xtgem.webuild.fstcawka.models.entities.AssignmentResult
import com.xtgem.webuild.fstcawka.models.entities.Semester
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.Subject

data class AssignmentContentAndResult(
    @Embedded val result: AssignmentResult,
    @Relation(
        parentColumn = "assignmentId",
        entityColumn = "assignmentId"
    )
    val assignment: List<AssignmentContent>
)

data class AssignmentWithResults(
    @Embedded val assignment: Assignment,
    @Relation(
        parentColumn = "assignmentId",
        entityColumn = "assignmentId"
    )
    val results: AssignmentResult
)

