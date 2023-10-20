package com.xtgem.webuild.fstcawka.misc

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.github.javafaker.Faker
import com.xtgem.webuild.fstcawka.database.FSTCAwkaDatabase
import com.xtgem.webuild.fstcawka.models.constants.Session
import com.xtgem.webuild.fstcawka.models.constants.getAllAssignmentHoldersModel
import com.xtgem.webuild.fstcawka.models.entities.Assignment
import com.xtgem.webuild.fstcawka.models.entities.AssignmentContent
import com.xtgem.webuild.fstcawka.models.entities.AssignmentResult
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.News
import com.xtgem.webuild.fstcawka.models.entities.PaymentDetail
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.entities.UserSession
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.Gender
import com.xtgem.webuild.fstcawka.models.enums.Grade
import com.xtgem.webuild.fstcawka.models.enums.NewsCategory
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.models.relations.ABillAndItsPaymentList
import com.xtgem.webuild.fstcawka.models.relations.ASubjectAndItsSemesters
import com.xtgem.webuild.fstcawka.models.relations.AllSubjectAndSAllSemesters
import com.xtgem.webuild.fstcawka.models.relations.AssignmentContentAndResult
import com.xtgem.webuild.fstcawka.models.relations.AssignmentWithResults
import com.xtgem.webuild.fstcawka.models.relations.CourseAndReaction
import com.xtgem.webuild.fstcawka.models.temp.TemporalNews
import com.xtgem.webuild.fstcawka.models.temp.news
import com.xtgem.webuild.fstcawka.models.temp.newsPoster
import com.xtgem.webuild.fstcawka.models.temp.wideBannerPoster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import java.time.LocalDateTime
import java.util.UUID

abstract class BaseRepository {
    abstract val database: FSTCAwkaDatabase

     fun getSubjectAndSemester(studentId: UUID): LiveData<DataResult<List<AllSubjectAndSAllSemesters>>>{
        val subjectAndSemester = MediatorLiveData<DataResult<List<AllSubjectAndSAllSemesters>>>()
        subjectAndSemester.value = DataResult(isLoading = true)
        val getSubjectAndSemester = database.studentDao().getSubjectAndSemester(studentId)
        subjectAndSemester.addSource(getSubjectAndSemester) { data ->
            subjectAndSemester.value = DataResult(data = data, isLoading = false)
        }
        return subjectAndSemester
    }

     fun getOneSubjectAndItsSemester(studentId: UUID, subject: Subjects): LiveData<DataResult<List<ASubjectAndItsSemesters>>>{
        val subjectAndSemester = MediatorLiveData<DataResult<List<ASubjectAndItsSemesters>>>()
        subjectAndSemester.value = DataResult(isLoading = true)
        val getSubjectAndSemester = database.studentDao().getOneSubjectAndItsSemester(studentId, subject)
        subjectAndSemester.addSource(getSubjectAndSemester) { data ->
            subjectAndSemester.value = DataResult(data = data, isLoading = false)
        }
        return subjectAndSemester
    }


     fun getOneBillAndItsPaymentList(studentId: UUID, bill: Bills): LiveData<DataResult<List<ABillAndItsPaymentList>>>{
        val billAndPayments = MediatorLiveData<DataResult<List<ABillAndItsPaymentList>>>()
        billAndPayments.value = DataResult(isLoading = true)
        val getBillAndPayments = database.studentDao().getOneBillAndItsPaymentList(studentId, bill)
        billAndPayments.addSource(getBillAndPayments) { data ->
            billAndPayments.value = DataResult(data = data, isLoading = false)
        }
        return billAndPayments
    }

     fun getAllNews(): LiveData<DataResult<List<News>>>{
        val allNews = MediatorLiveData<DataResult<List<News>>>()
         allNews.value = DataResult(isLoading = true)
        val getAllNews = database.otherDao().getAllNews()
         allNews.addSource(getAllNews) { data ->
             allNews.value = DataResult(data = data, isLoading = false)
        }
        return allNews
    }

    fun getNews(newsId: UUID): LiveData<DataResult<News>>{
        val news = MediatorLiveData<DataResult<News>>()
        news.value = DataResult(isLoading = true)
        val getNews = database.otherDao().getANews(newsId)
        news.addSource(getNews) { data ->
            news.value = DataResult(data = data, isLoading = false)
        }
        return news
    }

    fun getAllCourses(): LiveData<DataResult<List<Courses>>>{
        val allCourses = MediatorLiveData<DataResult<List<Courses>>>()
        allCourses.value = DataResult(isLoading = true)
        val getAllCourses = database.otherDao().getAllCourses()
        allCourses.addSource(getAllCourses) { data ->
            allCourses.value = DataResult(data = data, isLoading = false)
        }
        return allCourses
    }

    fun getCourseAndReaction(courseId: UUID): LiveData<DataResult<CourseAndReaction>>{
        val courseAndReaction = MediatorLiveData<DataResult<CourseAndReaction>>()
        courseAndReaction.value = DataResult(isLoading = true)
        val getCourseAndReaction = database.otherDao().getCourseAndReactions(courseId)
        courseAndReaction.addSource(getCourseAndReaction) { data ->
            courseAndReaction.value = DataResult(data = data, isLoading = false)
        }
        return courseAndReaction
    }

    private fun getSession(sessionToken: UUID): UserSession?{
        return database.studentDao().getUserSession(sessionToken)
    }

    suspend fun getAllBills(studentId: UUID, sessionToken: UUID): List<StudentBills>?{
        val scope = CoroutineScope(Dispatchers.Default)
        val session = scope.async { getSession(sessionToken) }.await()
        return if (session != null && session.sessionValidity()) {
            scope.cancel()
            database.studentDao().getBillsAndPaymentList(studentId)
        }else {
            scope.cancel()
            null
        }
    }


    suspend fun getAllBillsAndPaymentDetail(studentId: UUID, sessionToken: UUID, bill: Bills): ABillAndItsPaymentList?{
        val scope = CoroutineScope(Dispatchers.Default)
        val session = scope.async { getSession(sessionToken) }.await()
        return if (session != null && session.sessionValidity()) {
            scope.cancel()
            database.studentDao().getOneBillAndItsPaymentListNoLiveData(studentId, bill)
        }else {
            scope.cancel()
            null
        }
    }


    suspend fun getAlLAssignment(studentId: UUID, sessionToken: UUID): List<AssignmentWithResults>?{
        val scope = CoroutineScope(Dispatchers.Default)
        val session = scope.async { getSession(sessionToken) }.await()
        return if (session != null && session.sessionValidity()) {
            scope.cancel()
            database.studentDao().getAllAssignmentsWithResultsForStudent(studentId)
        }else {
            scope.cancel()
            null
        }
    }


    suspend fun getAssignmentContent(studentId: UUID, sessionToken: UUID, assignmentId: UUID): AssignmentContentAndResult?{
        val scope = CoroutineScope(Dispatchers.Default)
        val session = scope.async { getSession(sessionToken) }.await()
        return if (session != null && session.sessionValidity()) {
            scope.cancel()
            database.studentDao().getAssignmentContentAndResult(assignmentId, studentId)
        }else {
            scope.cancel()
            null
        }
    }


    fun getStudent(studentId: UUID, session: UserSession?): LiveData<DataResult<Student>>{
        val student = MediatorLiveData<DataResult<Student>>()
        student.value = DataResult(isLoading = true)
        val getStudent = database.studentDao().getStudent(studentId)
        student.addSource(getStudent) { data ->
            student.value = DataResult(data = data, isLoading = false)
        }
        return student
    }


    private fun addNews() {
        val allNews = mutableListOf<News>()
        for (i in news) {
            val wideBanner = (0..5).random() == 3
            val posters = if (wideBanner) wideBannerPoster else newsPoster
            posters.shuffle()
            val news = News(
                newsId = UUID.randomUUID(), createdDate = LocalDateTime.now(),
                title = i.title, description = i.description, content = i.content,
                wideBanner = wideBanner, i.category,
                imageLink = posters[0]
            )
            allNews.add(news)
        }
        allNews.forEach { database.otherDao().insertNews(it) }
    }

    private suspend fun addSchoolBills(studentId: UUID) {
        val billAndPaymentDetail = mutableMapOf<StudentBills, List<PaymentDetail>?>()
        for (bill in Bills.values()) {
            val total = 10000
            val paidAmount = (120..10000).random()
            val balance = total - paidAmount
            val unpaidPercentage = (((total - paidAmount.toDouble()) / total ) * 100).toInt()
            val studentBills = StudentBills(
                UUID.randomUUID(), studentId, bill, paidAmount, balance,
                unpaidPercentage, total, UUID.randomUUID()
            )
            billAndPaymentDetail[studentBills] = studentBills.generatePaymentDetail()
        }
        billAndPaymentDetail.forEach { bill ->
            database.studentDao().insertBill(bill.key)
            bill.value?.forEach { database.studentDao().insertPayment(it) }
        }
    }

    private fun generateAssignment(scope: CoroutineScope): List<Assignment>{
        val addedAssignment = mutableListOf<Assignment>()
        val allAssignments = getAllAssignmentHoldersModel()
        for (assignment in allAssignments) {
            val assignmentId = UUID.randomUUID()
            val assignmentContents = mutableListOf<AssignmentContent>()
            for (content in assignment.assignment) {
                val assignmentContent = AssignmentContent(
                    contentId = UUID.randomUUID(), assignmentId = assignmentId, assignment.assignment.indexOf(content),
                    question = content.question, options = content.options, answersIndex = content.answersIndex,
                )
                assignmentContents.add(assignmentContent)
            }
            val addAssignment = Assignment.createAssignment(
                scope = scope, title = assignment.subjects.subjectName, description = assignment.description,
                dueDateTime = Uncategorized().generateRandomLocalDateTime(true), subject = assignment.subjects,
                grades = assignment.grade, totalScore = assignment.assignment
                    .sumOf { it.point ?: (assignment.assignment.size * 3) },
                questions = assignmentContents
            )
            addedAssignment.add(addAssignment)
        }
        return addedAssignment
    }

    suspend fun generateAssignmentResultsFromDatabase(student: Student) {
        val studentAssignmentResult = mutableListOf<AssignmentResult>()
        val getStudentAssignment = database.studentDao().getAllAssignments()
        for (assignment in getStudentAssignment) {
            if (assignment.grades.contains(student.grade)) {
                val assignmentResult = AssignmentResult(
                    resultId = UUID.randomUUID(), assignmentId = assignment.assignmentId,
                    studentId = student.studentId, score = 0, questionSize = assignment.questionSize,
                    subjects = assignment.subject, creationDate = LocalDateTime.now()
                )
            }
        }
    }

    fun generateAssignmentResults(assignments: List<Assignment>, student: Student): List<AssignmentResult>{
        val studentAssignmentResult = mutableListOf<AssignmentResult>()
        for (assignment in assignments) {
            if (assignment.grades.contains(student.grade)) {
                val assignmentResult = AssignmentResult(
                    resultId = UUID.randomUUID(), assignmentId = assignment.assignmentId,
                    studentId = student.studentId, score = 0, questionSize = assignment.questionSize,
                    subjects = assignment.subject, creationDate = LocalDateTime.now()
                )
                studentAssignmentResult.add(assignmentResult)
            }
        }
        return studentAssignmentResult
    }

    // ae4c602d-a7aa-47af-a018-30ec6a5c67ca
    fun addTestStudent(course: Boolean, news: Boolean, bills: Boolean,
        assignment: Boolean) {
        val password = Uncategorized.hashPassword("111111")
        val faker = Faker()
        val emailDomains = Uncategorized().emailDomains
        emailDomains.shuffle()
        val student = Student(
            UUID.randomUUID(), "111111", password,
            faker.name().firstName(), faker.name().lastName(), 15,
            "${faker.name().username()}@${emailDomains[(0 until emailDomains.size).random()]}",
            faker.phoneNumber().cellPhone(), Grade.JS1, LocalDateTime.now(),
            Gender.NON_BINARY, imageLink = faker.avatar().image()
        )
        val courses = listOf(
            Courses(UUID.randomUUID(), "Computer Science", 21, 5, 1, "", newsPoster[0]),
            Courses(UUID.randomUUID(), "Politics", 53, 25, 11, "", newsPoster[1]),
            Courses(UUID.randomUUID(), "Astronomy", 90, 35, 21, "", newsPoster[2]),
            Courses(UUID.randomUUID(), "Calculus", 8, 1, 0, "", newsPoster[3]),
            Courses(UUID.randomUUID(), "Fine Arts", 15, 5, 3, "", newsPoster[4]),
            Courses(UUID.randomUUID(), "European History", 67, 15, 1, "", newsPoster[5]),
        )

        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            database.studentDao().insertStudent(student)
            if (course) courses.forEach { database.otherDao().insertCourse(it) }
            if (news) addNews()
            if (bills) addSchoolBills(student.studentId)
            if (assignment) {
                val assignments = generateAssignment(scope)
                generateAssignmentResults(assignments, student).forEach {
                    database.studentDao().insertAssignmentResult(it)
                }
            }
            scope.cancel()
        }
    }

}




/*

val currentStudentsQuiz  = mutableListOf<StudentAssignmentResult>()
                    val newAssignments = generateAssignments()
                    for (singleAssignment in newAssignments) {
                        if (singleAssignment.grade == grade) {
                            val assignmentResult = StudentAssignmentResult(
                                UUID.randomUUID(), singleAssignment.assignmentId, i.id, 0,
                                singleAssignment.subject, singleAssignment.assignment.size, mapOf(0 to null, 1 to null, 2 to null)
                            )
                            currentStudentsQuiz.add(assignmentResult)
                        }
                    }
                    currentStudentsQuiz.forEach { insertAssignmentResult(it) }


 */