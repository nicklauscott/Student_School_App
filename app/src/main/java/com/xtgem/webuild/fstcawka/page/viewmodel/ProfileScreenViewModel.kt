package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.UUID

class ProfileScreenViewModel(studentId: UUID): ViewModel() {
    private val repository = Repository.get()
    private val id = studentId

    val student: LiveData<DataResult<Student>> =
        repository.getStudent(studentId = id)

    fun insertStudent(student: Student) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            repository.database.studentDao().insertStudent(student)
            scope.cancel()
        }
    }
}

class ProfileScreenViewModelFactory(private val studentId: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileScreenViewModel(UUID.fromString(studentId)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
