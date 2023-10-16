package com.xtgem.webuild.fstcawka.misc

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.xtgem.webuild.fstcawka.database.FSTCAwkaDatabase
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.News
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.enums.Bills
import com.xtgem.webuild.fstcawka.models.enums.Gender
import com.xtgem.webuild.fstcawka.models.enums.Grade
import com.xtgem.webuild.fstcawka.models.enums.NewsCategory
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.models.relations.ABillAndItsPaymentList
import com.xtgem.webuild.fstcawka.models.relations.ASubjectAndItsSemesters
import com.xtgem.webuild.fstcawka.models.relations.AllSubjectAndSAllSemesters
import com.xtgem.webuild.fstcawka.models.relations.AssignmentContentAndResult
import com.xtgem.webuild.fstcawka.models.relations.CourseAndReaction
import com.xtgem.webuild.fstcawka.models.temp.TemporalNews
import com.xtgem.webuild.fstcawka.models.temp.news
import com.xtgem.webuild.fstcawka.models.temp.newsPoster
import com.xtgem.webuild.fstcawka.models.temp.wideBannerPoster
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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


     fun getAssignmentContentAndResult(assignmentId: UUID): LiveData<DataResult<List<AssignmentContentAndResult>>>{
        val assignmentContentAndResult = MediatorLiveData<DataResult<List<AssignmentContentAndResult>>>()
        assignmentContentAndResult.value = DataResult(isLoading = true)
        val getAssignmentContentAndResult = database.studentDao().getAssignmentContentAndResult(assignmentId)
        assignmentContentAndResult.addSource(getAssignmentContentAndResult) { data ->
            assignmentContentAndResult.value = DataResult(data = data, isLoading = false)
        }
        return assignmentContentAndResult
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
    fun getCourse(courseId: UUID): LiveData<DataResult<Courses>>{
        val course = MediatorLiveData<DataResult<Courses>>()
        course.value = DataResult(isLoading = true)
        val getCourse = database.otherDao().getACourse(courseId)
        course.addSource(getCourse) { data ->
            course.value = DataResult(data = data, isLoading = false)
        }
        return course
    }

    fun getStudent(studentId: UUID): LiveData<DataResult<Student>>{
        val student = MediatorLiveData<DataResult<Student>>()
        student.value = DataResult(isLoading = true)
        val getStudent = database.studentDao().getStudent(studentId)
        student.addSource(getStudent) { data ->
            student.value = DataResult(data = data, isLoading = false)
        }
        return student
    }

    fun getAllStudentByRegNO(studentId: UUID, regId: String): LiveData<DataResult<Student>>{
        val student = MediatorLiveData<DataResult<Student>>()
        student.value = DataResult(isLoading = true)
        val getStudent = database.studentDao().getStudentByRegNo(studentId, regId)
        student.addSource(getStudent) { data ->
            student.value = DataResult(data = data, isLoading = false)
        }
        return student
    }




    fun addNews() {
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
        GlobalScope.launch {
            allNews.forEach { database.otherDao().insertNews(it) }
        }
    }

    // ae4c602d-a7aa-47af-a018-30ec6a5c67ca
    fun addTestStudent(course: Boolean, news: Boolean) {
        val password = Uncategorized.hashPassword("111111")
        val student = Student(
            UUID.fromString("ae4c602d-a7aa-47af-a018-30ec6a5c67ca"), "111111", password,
            "Matt", "Parker", 15, "", "", Grade.JS1, LocalDateTime.now(),
            Gender.NON_BINARY
        )
        val courses = listOf(
            Courses(UUID.randomUUID(), "Computer Science", 21, 5, 1, "", newsPoster[0]),
            Courses(UUID.randomUUID(), "Politics", 53, 25, 11, "", newsPoster[1]),
            Courses(UUID.randomUUID(), "Astronomy", 90, 35, 21, "", newsPoster[2]),
            Courses(UUID.randomUUID(), "Calculus", 8, 1, 0, "", newsPoster[3]),
            Courses(UUID.randomUUID(), "Fine Arts", 15, 5, 3, "", newsPoster[4]),
            Courses(UUID.randomUUID(), "European History", 67, 15, 1, "", newsPoster[5]),
        )

        GlobalScope.launch {
            database.studentDao().insertStudent(student)
            if (course) courses.forEach { database.otherDao().insertCourse(it) }
            if (news) addNews()
        }
    }

}