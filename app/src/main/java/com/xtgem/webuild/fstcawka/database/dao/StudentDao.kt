package com.xtgem.webuild.fstcawka.database.dao

import androidx.lifecycle.LiveData
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
import com.xtgem.webuild.fstcawka.models.entities.StudentResult
import com.xtgem.webuild.fstcawka.models.entities.UserSession
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.models.relations.ABillAndItsPaymentList
import com.xtgem.webuild.fstcawka.models.relations.AssignmentContentAndResult
import com.xtgem.webuild.fstcawka.models.relations.AssignmentWithResults
import java.util.UUID

@Dao
interface StudentDao {

    @Query("DELETE FROM student")
    fun clearStudent()

    @Query("DELETE FROM StudentResult")
    fun clearSubject()

    @Query("DELETE FROM studentBills")
    fun clearBill()

    @Query("DELETE FROM paymentDetail")
    fun clearPayment()

    @Query("DELETE FROM assignment")
    fun clearAssignment()

    @Query("DELETE FROM assignmentResult")
    fun clearAssignmentResult()

    @Query("DELETE FROM assignmentContent")
    fun clearAssignmentContent()

    @Query("DELETE FROM userSession")
    fun clearUserSessions()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(studentResult: StudentResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBill(bill: StudentBills)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(paymentDetail: PaymentDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: Assignment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignmentResult(assignmentResult: AssignmentResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignmentContent(assignmentContent: AssignmentContent)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSession(userSession: UserSession)

    @Query("SELECT * FROM student")
    fun getAllStudent(): List<Student>

    @Query("SELECT * FROM student WHERE studentId = :studentId")
    fun getStudent(studentId: UUID): LiveData<Student>

    @Query("SELECT * FROM student WHERE studentId = :studentId")
    fun getStudentNoLiveData(studentId: UUID): Student

    @Query("SELECT * FROM StudentResult WHERE studentId = :studentId")
    fun getAllSubjectAndAllSemester(studentId: UUID): List<StudentResult>

    @Query("SELECT * FROM StudentResult WHERE studentId = :studentId AND semester = :semester")
    fun getAllSubjectAndOneSemester(studentId: UUID, semester: Semesters): List<StudentResult>?

    @Query("SELECT * FROM StudentResult WHERE studentId = :studentId AND subject = :subjects")
    fun getASubjectAndAllSemester(studentId: UUID, subjects: Subjects): List<StudentResult>

    @Query("SELECT * FROM StudentResult WHERE studentId = :studentId AND subject = :subjects AND semester = :semester")
    fun getASubjectAndASemester(studentId: UUID, subjects: Subjects, semester: Semesters): StudentResult

    @Query("SELECT * FROM student WHERE studentId = :studentId OR regId =:regId")
    fun getStudentByRegNo(studentId: UUID, regId: String): LiveData<Student>

    @Query("SELECT * FROM student WHERE studentId = :studentId OR regId =:regId")
    fun getStudentByRegNoNoLiveData(studentId: UUID, regId: String): Student?

    @Query("SELECT * FROM userSession WHERE sessionToken = :sessionToken")
    fun getUserSession(sessionToken: UUID): UserSession?


    @Query("SELECT * FROM userSession WHERE studentID =:studentID")
    fun getAllSessionForAUser(studentID: UUID): List<UserSession>?

    @Transaction
    @Query("SELECT * FROM studentBills WHERE studentId = :studentId")
    suspend fun getBillsAndPaymentList(studentId: UUID): List<StudentBills>

    @Transaction
    @Query("SELECT * FROM studentBills WHERE studentId = :studentId AND bill =:bill")
    fun getOneBillAndItsPaymentList(studentId: UUID, bill: Bills): LiveData<List<ABillAndItsPaymentList>>

    @Transaction
    @Query("SELECT * FROM studentBills WHERE studentId = :studentId AND bill =:bill")
    fun getOneBillAndItsPaymentListNoLiveData(studentId: UUID, bill: Bills): ABillAndItsPaymentList?

    @Transaction
    @Query("SELECT * FROM assignment")
    suspend fun getAllAssignments(): List<Assignment>

    @Transaction
    @Query("SELECT * FROM assignmentResult")
    suspend fun getAllAssignmentResults(): List<AssignmentResult>

    @Transaction
    @Query("SELECT * FROM assignmentResult WHERE assignmentId = :assignmentId AND studentId =:studentId ORDER BY creationDate")
    fun getAssignmentContentAndResult(assignmentId: UUID, studentId: UUID): AssignmentContentAndResult?

    @Transaction
    @Query("SELECT * FROM Assignment WHERE assignmentId IN (SELECT assignmentId FROM AssignmentResult WHERE studentId = :studentId)")
    fun getAllAssignmentsWithResultsForStudent(studentId: UUID): List<AssignmentWithResults>?


}
