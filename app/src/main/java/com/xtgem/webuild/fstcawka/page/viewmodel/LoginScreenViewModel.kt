package com.xtgem.webuild.fstcawka.page.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.misc.Uncategorized
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.constants.LoginStatus
import com.xtgem.webuild.fstcawka.models.constants.State
import com.xtgem.webuild.fstcawka.models.entities.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID



class LoginScreenViewModel: ViewModel() {
    private val repository = Repository.get()

    val status = CustomLiveData<LoginStatus>()


    fun verifyStudent2(regLogin: Boolean, regId: String, studentId: String, textPassword: String) {
        status.updateValue(LoginStatus(checking = true))
        viewModelScope.launch {
            delay(500L)
            if (regLogin) {
                val student = repository.getAllStudentByRegNO(studentId = UUID.randomUUID(), regId = regId)
                if (student.value?.data == null) {
                    status
                        .updateValue(LoginStatus(checking = false, status = false, reason = State.REG))

                }else {
                    Log.d("profilerings", "${student.value?.data}")
                    val checkPassword = Uncategorized.verifyPassword(textPassword, student.value!!.data!!.password)
                    if (!checkPassword) {
                        status
                            .updateValue(LoginStatus(checking = false, status = false, reason = State.Password))

                    }else {
                        status
                            .updateValue(LoginStatus(checking = false, status = true, reason = null))

                    }
                }
            }else {
                var studentUUID = UUID.randomUUID()
                try {
                    studentUUID = UUID.fromString(studentId)
                }catch (_: Exception) {
                    status
                        .updateValue(LoginStatus(checking = false, status = false, reason = State.ID))

                }
                val student = repository.getAllStudentByRegNO(studentId = studentUUID, regId = "")
                if (student.value?.data == null) {
                    status
                        .updateValue(LoginStatus(checking = false, status = false, reason = State.ID))

                }else {
                    val checkPassword = Uncategorized.verifyPassword(textPassword, student.value!!.data!!.password)
                    if (!checkPassword) {
                        status
                            .updateValue(LoginStatus(checking = false, status = false, reason = State.Password))

                    }else {
                        status
                            .updateValue(LoginStatus(checking = false, status = true, reason = null))

                    }
                }
            }
        }

    }

    fun verifyStudent(regLogin: Boolean, regId: String, studentId: String, textPassword: String) {
        status.updateValue(LoginStatus(checking = true))
        val scope = CoroutineScope(Dispatchers.IO)
        var student: Student? = null
            scope.launch {
            if (regLogin) {
                student = repository.database.studentDao().getStudentByRegNoNoLiveData(UUID.randomUUID(), regId)
            }
            else {
                var userUUID = UUID.randomUUID()
                try {
                    userUUID = UUID.fromString(studentId)
                }catch (_: Exception) { }
                student = repository.database.studentDao().getStudentByRegNoNoLiveData(userUUID, regId)
            }
             scope.cancel()
        }
        viewModelScope.launch {
            delay(500L)
                if (student == null) {
                    if (regLogin)
                        status.updateValue(LoginStatus(checking = false, status = false, reason = State.REG))
                    else
                        status.updateValue(LoginStatus(checking = false, status = false, reason = State.ID))
                }else {
                    val isPassword = Uncategorized.verifyPassword(textPassword, student!!.password)
                    if (isPassword) status.updateValue(LoginStatus(checking = true, status = true, userId = student!!.studentId.toString()))
                    else status.updateValue(LoginStatus(checking = false, status = false, reason = State.Password))
                }
        }

    }
}
