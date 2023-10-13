package com.xtgem.webuild.fstcawka.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.xtgem.webuild.fstcawka.models.entities.Assignment
import com.xtgem.webuild.fstcawka.models.entities.AssignmentContent
import com.xtgem.webuild.fstcawka.models.entities.AssignmentResult
import com.xtgem.webuild.fstcawka.models.entities.PaymentDetail
import com.xtgem.webuild.fstcawka.models.entities.Semester
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.entities.Subject
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.models.relations.ABillAndItsPaymentList
import com.xtgem.webuild.fstcawka.models.relations.ASubjectAndItsSemesters
import com.xtgem.webuild.fstcawka.models.relations.AllSubjectAndSAllSemesters
import com.xtgem.webuild.fstcawka.models.relations.AssignmentContentAndResult
import java.util.UUID

@Dao
interface StudentDao {

    @Query("DELETE FROM student")
    fun clearStudent()

    @Query("DELETE FROM subject")
    fun clearSubject()

    @Query("DELETE FROM semester")
    fun clearSemester()

    @Query("DELETE FROM studentBills")
    fun clearBill()

    @Query("DELETE FROM paymentDetail")
    fun clearPayment()

    @Query("DELETE FROM assignment")
    fun clearAssignment()

    @Query("DELETE FROM assignmentContent")
    fun clearAssignmentContent()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSemester(semester: Semester)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBill(bill: StudentBills)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(paymentList: PaymentDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: Assignment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignmentContent(assignmentContent: AssignmentContent)

    @Query("SELECT * FROM student")
    fun getAllStudent(): List<Student>

    @Query("SELECT * FROM student WHERE studentId = :studentId")
    suspend fun getStudent(studentId: UUID): Student

    @Transaction
    @Query("SELECT * FROM subject WHERE studentId = :studentId")
    suspend fun getSubjectAndSemester(studentId: UUID): List<AllSubjectAndSAllSemesters>

    @Transaction
    @Query("SELECT * FROM subject WHERE studentId = :studentId AND subjects =:subject")
    suspend fun getOneSubjectAndItsSemester(studentId: UUID, subject: Subjects): List<ASubjectAndItsSemesters>

    @Transaction
    @Query("SELECT * FROM studentBills WHERE studentId = :studentId")
    suspend fun getBillsAndPaymentList(studentId: UUID): List<StudentBills>

    @Transaction
    @Query("SELECT * FROM studentBills WHERE studentId = :studentId AND bill =:bill")
    suspend fun getOneBillAndItsPaymentList(studentId: UUID, bill: Bills): List<ABillAndItsPaymentList>

    @Transaction
    @Query("SELECT * FROM assignment")
    suspend fun getAllAssignments(): List<Assignment>

    @Transaction
    @Query("SELECT * FROM assignmentResult")
    suspend fun getAllAssignmentResults(): List<AssignmentResult>

    @Transaction
    @Query("SELECT * FROM assignmentResult WHERE assignmentId = :assignmentId ORDER BY creationDate")
    suspend fun getAssignmentContentAndResult(assignmentId: UUID): List<AssignmentContentAndResult>



}
