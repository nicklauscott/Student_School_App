package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.StudentResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class ResultDetailScreenViewModel(val studentId: UUID, val sessionToken: UUID, val resultId: UUID): ViewModel() {
    val repository = Repository.get()
    val result: CustomLiveData<DataResult<StudentResult>> = CustomLiveData()
    val student: CustomLiveData<DataResult<Student>> = CustomLiveData()

    init {
        getResultAndStudent()
    }

    private fun getResultAndStudent() {
        viewModelScope.launch(Dispatchers.Default) {
            val getResult = repository.getResultById(studentId, sessionToken, resultId)
            val getStudent = repository.getStudent(studentId, sessionToken)
            withContext(Dispatchers.Main) {
                if (getResult != null && getStudent != null) {
                    result.updateValue(DataResult(isLoading = false, data = getResult))
                    student.updateValue(DataResult(isLoading = false, data = getStudent))
                }else {
                    result.updateValue(DataResult(isLoading = false))
                    student.updateValue(DataResult(isLoading = false))
                }
            }
        }
    }
}

class ResultDetailScreenViewModelFactory(private val studentId: String,
           private val sessionToken: String, private val resultId: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultDetailScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultDetailScreenViewModel(UUID.fromString(studentId), UUID.fromString(sessionToken),
                UUID.fromString(resultId)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}