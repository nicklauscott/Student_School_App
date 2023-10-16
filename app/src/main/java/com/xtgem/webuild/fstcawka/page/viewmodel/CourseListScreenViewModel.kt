package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.News
import java.util.UUID

class CourseListScreenViewModel: ViewModel() {
    private val repository = Repository.get()

    val course: LiveData<DataResult<List<Courses>>> =
        repository.getAllCourses()
}
