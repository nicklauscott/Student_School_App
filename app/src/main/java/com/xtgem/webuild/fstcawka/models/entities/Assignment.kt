package com.xtgem.webuild.fstcawka.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.enums.Grade
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class Assignment(
    @PrimaryKey val assignmentId: UUID,
    val title: String,
    val description: String,
    val dueDateTime: LocalDateTime,
    val creationDateTime: LocalDateTime,
    val subject: Subjects,
    val questionSize: Int,
    val totalScore: Int,
    val grade: Grade,
) {
    companion object {
        fun createAssignment(
            scope: CoroutineScope,
            title: String,
            description: String,
            dueDateTime: LocalDateTime,
            subject: Subjects,
            totalScore: Int,
            grade: Grade,
            questions: List<AssignmentContent>
        ): Assignment{
            val repository = Repository.get()
            val assignmentID = UUID.randomUUID()
            val assignmentContent = mutableListOf<AssignmentContent>()
            var index = 0
            questions.forEach {
                assignmentContent.add(it.copy(assignmentId = assignmentID, questionIndex = index))
                index += 1
            }
            val assignment = Assignment(
                assignmentID, title, description, dueDateTime, LocalDateTime.now(),
                subject, questions.size, totalScore, grade)
            scope.launch {
                repository.database.studentDao().insertAssignment(assignment)
                assignmentContent.forEach { repository.database.studentDao().insertAssignmentContent(it) }
            }
            return assignment
        }
    }
}
