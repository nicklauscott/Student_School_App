package com.xtgem.webuild.fstcawka.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.News
import java.util.UUID

@Dao
interface OtherDao {

    @Query("DELETE FROM news")
    fun clearNews()

    @Query("DELETE FROM courses")
    fun clearCourses()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(courses: Courses)

    @Query("SELECT * FROM news")
    fun getAllNews(): List<News>

    @Query("SELECT * FROM courses")
    fun getAllCourses(): List<Courses>

    @Query("SELECT * FROM news WHERE newsId =:newsId")
    fun getANews(newsId: UUID): News

    @Query("SELECT * FROM courses WHERE courseId =:courseId")
    fun getACourse(courseId: UUID): Courses

}