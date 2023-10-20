package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.entities.AssignmentResult
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.relations.AssignmentContentAndResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class AssignmentDetailViewModel(val studentId: UUID, val sessionToken: UUID, val assignmentId: UUID): ViewModel() {
    val repository = Repository.get()

    val questions: CustomLiveData<DataResult<AssignmentContentAndResult>> = CustomLiveData()

    init {
        getQuestions()
    }
    private fun getQuestions() {
        viewModelScope.launch(Dispatchers.Default) {
            val getQuestion = repository.getAssignmentContent(studentId, sessionToken, assignmentId)
            withContext(Dispatchers.Main) {
                if (getQuestion != null) {
                    questions.updateValue(DataResult(isLoading = false, data = getQuestion))
                }else {
                    questions.updateValue(DataResult(isLoading = false, sessionInvalid = false))
                }
            }
        }
    }

    fun uploadResult(score: Int, questionIndex: Int, answeredIndex: Int) {
        val canSave: Boolean?
        var oldScore = 0
        val newCompleteList = questions.value!!.data!!.result.questionWithAnsweredIndex.toMutableMap()
        if (newCompleteList[questionIndex] == null) {
            canSave = true
            oldScore = questions.value!!.data?.result?.score ?: 0
            newCompleteList[questionIndex] = answeredIndex
        }else {
            canSave = false
        }

        val studentAssignmentResult = AssignmentResult(
            resultId = questions.value!!.data!!.result.resultId,
            assignmentId = questions.value!!.data!!.result.assignmentId,
            score = oldScore + score, studentId = questions.value!!.data!!.result.studentId,
            subjects = questions.value!!.data!!.result.subjects,
            questionSize = questions.value!!.data!!.result.questionSize,
            questionWithAnsweredIndex = newCompleteList)
        if (canSave) {
            viewModelScope.launch {
                repository.database.studentDao().insertAssignmentResult(studentAssignmentResult)
                getQuestions()
            }
        }
    }

}


class AssignmentDetailViewModelFactory(private val studentId: String,
                                       private val sessionToken: String,
                                       private val assignmentId: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssignmentDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssignmentDetailViewModel(UUID.fromString(studentId),
                UUID.fromString(sessionToken), UUID.fromString(assignmentId)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}