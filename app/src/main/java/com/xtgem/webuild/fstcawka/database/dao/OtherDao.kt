package com.xtgem.webuild.fstcawka.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.xtgem.webuild.fstcawka.models.entities.CourseReactions
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.News
import com.xtgem.webuild.fstcawka.models.relations.AssignmentContentAndResult
import com.xtgem.webuild.fstcawka.models.relations.CourseAndReaction
import java.util.UUID

@Dao
interface OtherDao {

    @Query("DELETE FROM news")
    fun clearNews()

    @Query("DELETE FROM courses")
    fun clearCourses()

    @Query("DELETE FROM courseReactions")
    fun clearCoursesReaction()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(courses: Courses)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourseReaction(courseReaction: CourseReactions)

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<News>>

    @Query("SELECT * FROM courses")
    fun getAllCourses(): LiveData<List<Courses>>

    @Query("SELECT * FROM news WHERE newsId =:newsId")
    fun getANews(newsId: UUID): LiveData<News>

    @Query("SELECT * FROM courses WHERE courseId =:courseId")
    fun getACourse(courseId: UUID): LiveData<Courses>

    @Transaction
    @Query("SELECT * FROM courses WHERE courseId = :courseId")
    fun getCourseAndReactions(courseId: UUID): LiveData<CourseAndReaction>

}