package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class ProfileScreenViewModel(val studentId: UUID, val sessionToken: UUID): ViewModel() {
    private val repository = Repository.get()

    val student: CustomLiveData<DataResult<Student>> = CustomLiveData()

    init {
        getStudent()
    }


    private fun getSession() = repository.database.studentDao().getUserSession(sessionToken)
    private fun getStudent() {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch { // launch coroutine to make background request
            delay(500L)
            val session = getSession()
            if (session != null && session.sessionValidity()) {
                val getStudent = repository.database.studentDao().getStudentNoLiveData(studentId)
                withContext(Dispatchers.Main) {// switched to main thread with context to set value
                    student.updateValue(DataResult(data = getStudent, isLoading = false, sessionInvalid = true))
                }
            }else {
                withContext(Dispatchers.Main) {
                    student.updateValue(DataResult(isLoading = false, sessionInvalid = false))
                }

            }
            scope.cancel()
        }
    }

    fun insertStudent(student: Student) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            repository.database.studentDao().insertStudent(student)
            scope.cancel()
        }
    }

    private fun cancelSessionToken() {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            val session = repository.database.studentDao().getUserSession(sessionToken)
            if (session != null) {
                repository.database.studentDao().insertUserSession(session.copy(active = false))
            }
            scope.cancel()
        }
    }

    fun logoutStudent() {
        cancelSessionToken()
    }

}

class ProfileScreenViewModelFactory(private val studentId: String, private val sessionToken: String): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileScreenViewModel(UUID.fromString(studentId), UUID.fromString(sessionToken)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
