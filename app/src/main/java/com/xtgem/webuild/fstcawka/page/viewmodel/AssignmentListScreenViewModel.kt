package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.relations.AssignmentWithResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class AssignmentListScreenViewModel(val studentId: UUID, val sessionToken: UUID): ViewModel() {
    val repository = Repository.get()

    val assignments: CustomLiveData<DataResult<List<AssignmentWithResults>>> = CustomLiveData()

    init {
        getAllAssignment()
    }

    private fun getAllAssignment() {
        viewModelScope.launch(Dispatchers.Default) {
            val getAssignment = repository.getAlLAssignment(studentId, sessionToken)
            withContext(Dispatchers.Main) {
                if (getAssignment != null) {
                    assignments.updateValue(DataResult(data = getAssignment, isLoading = false))
                }else{
                    assignments.updateValue(DataResult(isLoading = false, sessionInvalid = false))
                }
            }
        }
    }
}

class AssignmentListScreenViewModelFactory(private val userId: String,
                                         private val studentId: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssignmentListScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssignmentListScreenViewModel(UUID.fromString(userId), UUID.fromString(studentId)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}