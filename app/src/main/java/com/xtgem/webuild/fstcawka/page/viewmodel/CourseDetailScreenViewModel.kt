package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.entities.CourseReactions
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.News
import com.xtgem.webuild.fstcawka.models.enums.CourseReaction
import com.xtgem.webuild.fstcawka.models.relations.CourseAndReaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

class CourseDetailScreenViewModel(courseId: UUID, studentId: UUID): ViewModel() {
    private val repository = Repository.get()
    private val id = courseId
    private val userId = studentId

    val course: LiveData<DataResult<CourseAndReaction>> =
        repository.getCourseAndReaction(courseId)

    fun react(like: Boolean): Boolean?{
        val scope = CoroutineScope(Dispatchers.Default)
        // check if user already reacted
        course.value?.data?.reactions?.find { it.reaction == CourseReaction.Like && it.courseId == id && it.studentId == userId
                || it.reaction == CourseReaction.DisLike && it.courseId == id && it.studentId == userId }
            ?: if (like) {
                val updateLikeCount = course.value?.data?.course?.likedAmount
                val newCourse = course.value?.data?.course?.copy(likedAmount = updateLikeCount?.plus(1) ?: 0)
                // insert Reaction
                val reaction = CourseReactions(UUID.randomUUID(), id, userId, CourseReaction.Like)
                if (newCourse != null) {
                    scope.launch {
                        delay(1000L)
                        repository.database.otherDao().insertCourse(newCourse)
                        repository.database.otherDao().insertCourseReaction(reaction)
                        scope.cancel()
                    }
                }
                return true
            }else {
                val updateDislikeCount = course.value?.data?.course?.dislikeAmount
                val newCourse = course.value?.data?.course?.copy(dislikeAmount = updateDislikeCount?.plus(1) ?: 0)
                // insert Reaction
                val reaction = CourseReactions(UUID.randomUUID(), id, userId, CourseReaction.DisLike)
                if (newCourse != null) {
                    scope.launch {
                        delay(1000L)
                        repository.database.otherDao().insertCourse(newCourse)
                        repository.database.otherDao().insertCourseReaction(reaction)
                        scope.cancel()
                    }
                }
                return false
            }
        return null
    }

    fun reactTest(like: Boolean): Boolean{
        val scope = CoroutineScope(Dispatchers.Default)
        // check if user already reacted
        val reacted = course.value?.data?.reactions?.find { it.reaction == CourseReaction.Like && it.courseId == id && it.studentId == userId
                || it.reaction == CourseReaction.DisLike && it.courseId == id && it.studentId == userId }
        if (reacted != null) {
            if (like) {
                val updateLikeCount = course.value?.data?.course?.likedAmount
                val updateDislikeCount = course.value?.data?.course?.dislikeAmount
                val newCourse = course.value?.data?.course?.copy(likedAmount = updateLikeCount?.plus(1) ?: 0,
                    dislikeAmount = updateDislikeCount?.minus(1) ?: 0)
                // insert Reaction
                val reaction = CourseReactions(UUID.randomUUID(), id, userId, CourseReaction.Like)
                if (newCourse != null) {
                    scope.launch {
                        delay(1000L)
                        repository.database.otherDao().insertCourse(newCourse)
                        repository.database.otherDao().insertCourseReaction(reaction)
                        scope.cancel()
                    }
                }
                return true
            }else {
                val updateDislikeCount = course.value?.data?.course?.dislikeAmount
                val updateLikeCount = course.value?.data?.course?.likedAmount
                val newCourse = course.value?.data?.course?.copy(dislikeAmount = updateDislikeCount?.plus(1) ?: 0,
                    likedAmount = updateLikeCount?.minus(1) ?: 0)
                // insert Reaction
                val reaction = CourseReactions(UUID.randomUUID(), id, userId, CourseReaction.Like)
                if (newCourse != null) {
                    scope.launch {
                        delay(1000L)
                        repository.database.otherDao().insertCourse(newCourse)
                        repository.database.otherDao().insertCourseReaction(reaction)
                        scope.cancel()
                    }
                }
                return false
            }
        }else {
            if (like) {
                val updateLikeCount = course.value?.data?.course?.likedAmount
                val newCourse = course.value?.data?.course?.copy(likedAmount = updateLikeCount?.plus(1) ?: 0)
                // insert Reaction
                val reaction = CourseReactions(UUID.randomUUID(), id, userId, CourseReaction.Like)
                if (newCourse != null) {
                    scope.launch {
                        delay(1000L)
                        repository.database.otherDao().insertCourse(newCourse)
                        repository.database.otherDao().insertCourseReaction(reaction)
                        scope.cancel()
                    }
                }
                return true
            }else {
                val updateDislikeCount = course.value?.data?.course?.dislikeAmount
                val newCourse = course.value?.data?.course?.copy(dislikeAmount = updateDislikeCount?.plus(1) ?: 0)
                // insert Reaction
                val reaction = CourseReactions(UUID.randomUUID(), id, userId, CourseReaction.Like)
                if (newCourse != null) {
                    scope.launch {
                        delay(1000L)
                        repository.database.otherDao().insertCourse(newCourse)
                        repository.database.otherDao().insertCourseReaction(reaction)
                        scope.cancel()
                    }
                }
                return false
            }
        }

    }

    init {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            delay(1000L)
            val read = course.value?.data?.reactions?.find { it.courseId == id
                    && it.reaction == CourseReaction.Read && it.studentId == userId }
            val updateViewCount = course.value?.data?.course?.readAmount
            val newCourse = course.value?.data?.course?.copy(readAmount = updateViewCount?.plus(1) ?: 0)
            val reaction = CourseReactions(UUID.randomUUID(), id, userId, CourseReaction.Read)
            if (read == null && newCourse != null) {
                repository.database.otherDao().insertCourse(newCourse)
                repository.database.otherDao().insertCourseReaction(reaction)
            }
            scope.cancel()
        }
    }

}


class CourseDetailScreenViewModelFactory(private val courseId: String,
                                         private val studentId: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseDetailScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseDetailScreenViewModel(UUID.fromString(courseId), UUID.fromString(studentId)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}