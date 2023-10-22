package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.StudentResult
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class ResultScreenViewModel(val studentId: UUID, val sessionToken: UUID,
                            val subject: Subjects, val semester: Semesters): ViewModel(){
    val repository = Repository.get()
    val results: CustomLiveData<DataResult<List<StudentResult>>> = CustomLiveData()
    val student: CustomLiveData<DataResult<Student>> = CustomLiveData()

    init {
        getResult()
    }
    private fun getResult() {
        viewModelScope.launch(Dispatchers.Default) {
            if (subject == Subjects.All && semester == Semesters.All) {
                val getResult = repository.getStudentAllResult(studentId, sessionToken)
                val getStudent = repository.getStudent(studentId, sessionToken)
                withContext(Dispatchers.Main) {
                    results.updateValue(DataResult(isLoading = false, data = getResult))
                    student.updateValue(DataResult(isLoading = false, data = getStudent))
                }
            }else if (subject == Subjects.All) {
                val getResult = repository.getAllSubjectAndOneSemester(studentId, sessionToken, semester)
                val getStudent = repository.getStudent(studentId, sessionToken)
                withContext(Dispatchers.Main) {
                    results.updateValue(DataResult(isLoading = false, data = getResult))
                    student.updateValue(DataResult(isLoading = false, data = getStudent))
                }
            }else if (semester == Semesters.All) {
                val getResult = repository.getASubjectAndAllSemester(studentId, sessionToken, subject)
                val getStudent = repository.getStudent(studentId, sessionToken)
                withContext(Dispatchers.Main) {
                    results.updateValue(DataResult(isLoading = false, data = getResult))
                    student.updateValue(DataResult(isLoading = false, data = getStudent))
                }
            }else {
                val getResult = repository.getASubjectAndASemester(studentId, sessionToken, subject, semester)
                val getStudent = repository.getStudent(studentId, sessionToken)
                withContext(Dispatchers.Main) {
                    if (getResult != null) {
                        val subjectList = listOf(getResult)
                        results.updateValue(DataResult(isLoading = false, data = subjectList))
                        student.updateValue(DataResult(isLoading = false, data = getStudent))
                    }else {
                        results.updateValue(DataResult(isLoading = false))
                    }
                }
            }
        }
    }
}

class ResultScreenViewModelFactory(private val studentId: String,
                                   private val sessionToken: String,
                                   private val subject: String, private val semester: String): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultScreenViewModel(UUID.fromString(studentId), UUID.fromString(sessionToken),
                Subjects.valueOf(subject), Semesters.valueOf(semester)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}