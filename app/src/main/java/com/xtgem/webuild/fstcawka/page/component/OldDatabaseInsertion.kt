package com.xtgem.webuild.fstcawka.page.component

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.entities.Semester
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.Subject
import com.xtgem.webuild.fstcawka.models.enums.Gender
import com.xtgem.webuild.fstcawka.models.enums.Grade
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.ui.theme.FSTCAWKATheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID


@Composable
fun OldDatabaseInsertion() {

    FSTCAWKATheme {
        val repository =  Repository.get().database.studentDao()
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            // add students
            val students = listOf(
                Student(
                    UUID.randomUUID(), "11152", "pass3928", "Mark","Barry", 14, "", "",
                    Grade.JS1, LocalDateTime.now(), Gender.FEMALE),
                Student(
                    UUID.randomUUID(), "83422", "pass2717", "Phil","Carter", 12, "", "",
                    Grade.JS1, LocalDateTime.now(), Gender.MALE),
                Student(
                    UUID.randomUUID(), "09152", "pass8343", "Smith","Johnson", 17, "", "",
                    Grade.JS1, LocalDateTime.now(), Gender.FEMALE),
                Student(
                    UUID.randomUUID(), "17281", "pass9899", "Jane","Bart", 11, "", "",
                    Grade.JS1, LocalDateTime.now(), Gender.FEMALE),
                Student(
                    UUID.randomUUID(), "49822", "pass2323", "Rowe","Jenkins", 15, "", "",
                    Grade.JS1, LocalDateTime.now(), Gender.MALE)
            )

            val subjects = mutableListOf<Subject>()
            val semesters = mutableListOf<Semester>()
            for (i in students) {
                for (v in Subjects.values()) {
                    val subjectId = UUID.randomUUID()

                    for (s in Semesters.values()) {

                        // add semester
                        val semester = Semester(
                            UUID.randomUUID(), subjectId, v, i.studentId, s, (5..10).random(),
                            (5..10).random(), (7..20).random(), (25..60).random(), 100)
                        semesters.add(semester)
                    }

                    // add subject
                    val subject = Subject(subjectId, v, i.studentId)
                    subjects.add(subject)
                }
            }


            // screens
            GlobalScope.launch{
//                            students.forEach { repository.insertStudent(it) }
//                            subjects.forEach { repository.insertSubject(it) }
//                            semesters.forEach { repository.insertSemester(it) }

                var users = repository.getAllStudent()
                Log.d("testing", "b: $users")


                val x = if (users.isNotEmpty()) users.first() else null
                Log.d("testing", "2: ${x}")

//                if (x != null) {
//                    val sub = repository.getOneSubjectAndItsSemester(x.studentId, Subjects.Maths)
//                    Log.d("testing", "3: ${sub}")
//                    for (i in sub.value) {
//                        for (s in i.semester) {
//                            Log.d("testing", "$s")
//                        }
//                    }
//                }
            }



        }
    }
}
