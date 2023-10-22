package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class HomeScreenViewModel(val studentId: UUID, val sessionToken: UUID): ViewModel() {
    val repository = Repository.get()

    private var globalTime = "12:00"
    val student: CustomLiveData<DataResult<Student>> = CustomLiveData()
    val greeting: CustomLiveData<DataResult<String>> = CustomLiveData()

    init {
        convertGlobalTime()
        getAStudent()
    }
    private fun getAStudent() {
        viewModelScope.launch(Dispatchers.Default) {
            val getStudent = repository.getStudent(studentId, sessionToken)
            withContext(Dispatchers.Main) {
                if (getStudent != null) {
                    student.updateValue(DataResult(data = getStudent, isLoading = false))
                }else {
                    student.updateValue(DataResult(isLoading = false, sessionInvalid = false))
                }
            }
        }
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.Default) {
            val getSession = repository.database.studentDao().getUserSession(sessionToken)?.copy(active = false)
            if (getSession != null) {
                repository.database.studentDao().insertUserSession(getSession)
            }
        }
    }

    private fun convertGlobalTime() {
        val timeList = mutableListOf<String>()
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formattedTime =  currentTime.format(formatter).toString()

        formattedTime.map { timeList.add(it.toString()) }
        val tempTimeList = mutableListOf<String>()
        for (i in timeList) {
            if (i == ":") {
                break
            }
            tempTimeList.add(i)
        }
        val hourOfDay = tempTimeList.joinToString(separator = "").toInt()
        globalTime = formattedTime
        when (hourOfDay) {
            in 0..11 -> {
                greeting.updateValue(DataResult(data = "Good Morning"))
            }
            in 12..18 -> {
                greeting.updateValue(DataResult(data = "Good Afternoon"))
            }
            else -> {
                greeting.updateValue(DataResult(data = "Good Evening"))
            }
        }

    }

}

class HomeScreenViewModelFactory(
    private val studentId: String,
    private val sessionToken: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeScreenViewModel(UUID.fromString(studentId),
                UUID.fromString(sessionToken)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}