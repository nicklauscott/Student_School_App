package com.xtgem.webuild.fstcawka.page.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.UserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import java.util.UUID

class ProfileScreenViewModel(val studentId: UUID, val sessionToken: UUID): ViewModel() {
    private val repository = Repository.get()

    val student: CustomLiveData<DataResult<Student>> = CustomLiveData()

    init {
        Log.d("profile-rings", sessionToken.toString())
        getStudent()
    }


    private fun getSession() = repository.database.studentDao().getUserSession(sessionToken)
    fun getStudent() {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch { // launch coroutine to make background request
            delay(500L)
            val session = getSession()
            Log.d("profile-rings", "1: $session")
            if (session != null && session.sessionValidity()) {
                Log.d("profile-rings", "2: $session")
                val getStudent = repository.database.studentDao().getStudentNoLiveData(studentId)
                withContext(Dispatchers.Main) {// switched to main thread with context to set value
                    Log.d("profile-rings", "3: $session")
                    student.updateValue(DataResult(data = getStudent, isLoading = false, sessionInvalid = true))
                }
            }else {
                Log.d("profile-rings", "4: $session")
                withContext(Dispatchers.Main) {
                    Log.d("profile-rings", "5: $session")// switched to main thread with context to set value
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
        //getStudent()
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
