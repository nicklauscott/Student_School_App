package com.xtgem.webuild.fstcawka.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.xtgem.webuild.fstcawka.models.entities.CourseReactions
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.PaymentDetail
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.enums.Bills

data class CourseAndReaction(
    @Embedded val course: Courses,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "courseId"
    )
    val reactions: List<CourseReactions>
)

